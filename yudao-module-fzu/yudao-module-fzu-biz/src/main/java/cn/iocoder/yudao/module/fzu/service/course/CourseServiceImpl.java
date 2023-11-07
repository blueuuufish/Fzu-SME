package cn.iocoder.yudao.module.fzu.service.course;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.fzu.convert.coursestudent.CourseStudentConvert;
import cn.iocoder.yudao.module.fzu.convert.student.StudentConvert;
import cn.iocoder.yudao.module.fzu.dal.dataobject.coursestudent.CourseStudentDO;
import cn.iocoder.yudao.module.fzu.dal.dataobject.student.StudentDO;
import cn.iocoder.yudao.module.fzu.dal.mysql.coursestudent.CourseStudentMapper;
import cn.iocoder.yudao.module.fzu.dal.mysql.student.StudentMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import cn.iocoder.yudao.module.fzu.controller.admin.course.vo.*;
import cn.iocoder.yudao.module.fzu.dal.dataobject.course.CourseDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.fzu.convert.course.CourseConvert;
import cn.iocoder.yudao.module.fzu.dal.mysql.course.CourseMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.fzu.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 课程 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private CourseStudentMapper courseStudentMapper;

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Integer createCourse(CourseCreateReqVO createReqVO) {
        // 插入
        CourseDO course = CourseConvert.INSTANCE.convert(createReqVO);
        courseMapper.insert(course);
        // 返回
        return course.getId();
    }

    @Override
    public void updateCourse(CourseUpdateReqVO updateReqVO) {
        // 校验存在
        validateCourseExists(updateReqVO.getId());
        // 更新
        CourseDO updateObj = CourseConvert.INSTANCE.convert(updateReqVO);
        courseMapper.updateById(updateObj);
    }

    @Override
    public void deleteCourse(Integer id) {
        // 校验存在
        validateCourseExists(id);
        // 删除
        courseMapper.deleteById(id);
    }

    private void validateCourseExists(Integer id) {
        if (courseMapper.selectById(id) == null) {
            throw exception(COURSE_NOT_EXISTS);
        }
    }

    @Override
    public CourseDO getCourse(Integer id) {
        return courseMapper.selectById(id);
    }

    @Override
    public List<CourseDO> getCourseList(Collection<Integer> ids) {
        return courseMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CourseDO> getCoursePage(CoursePageReqVO pageReqVO) {
        return courseMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CourseDO> getCourseList(CourseExportReqVO exportReqVO) {
        return courseMapper.selectList(exportReqVO);
    }

    /*
    * 导入研究生课程表
    * */
    @Override
    @Transactional(rollbackFor = Exception.class) // 添加事务，异常则回滚所有导入
    public CourseUploadResVO uploadCourseExcel(List<CourseUploadExcelVO> uploadCourses, String originalFilename) {
        if (CollUtil.isEmpty(uploadCourses)) throw exception(COURSE_IS_EMPTY);

        // TODO: 处理主讲和辅讲问题; 时间地点问题
        ArrayList<CourseUploadExcelVO> handledList = new ArrayList<>();
        for (CourseUploadExcelVO uploadCourse : uploadCourses) {
            String teacher = uploadCourse.getTeacher();
            if (teacher.contains("辅讲：")) {
                String[] parts = teacher.split("辅讲：");
                if (parts.length == 2) {
                    String mainTeacher = parts[0].replace("主讲：", "").trim(); // 获取主讲名字
                    String assistantTeacher = parts[1].trim(); // 获取辅讲名字
                    uploadCourse.setTeacher(mainTeacher);
                    handledList.add(uploadCourse);
                    CourseUploadExcelVO newUploadCourse = uploadCourse.toBuilder().build();
                    newUploadCourse.setTeacher(assistantTeacher); // 设置辅讲名字
                    // 添加辅讲对象到列表中
                    handledList.add(newUploadCourse);
                }
            } else {
                String mainTeacher = uploadCourse.getTeacher().replace("主讲：", "").trim();
                uploadCourse.setTeacher(mainTeacher);
                handledList.add(uploadCourse);
            }
        }
        System.out.println(handledList);

        CourseUploadResVO courseUploadResVO = CourseUploadResVO.builder()
                .createCourseNames(new ArrayList<>())
                .updateCourseNames(new ArrayList<>())
                .failureCourseNames(new HashMap<>()).build();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                6,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(8),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy() // 设置饱和策略为CallerRunsPolicy
        );

        try {
            for (CourseUploadExcelVO uploadCourse : handledList) {
                threadPoolExecutor.execute(() -> {
                    // TODO: 暂时不需要校验和异常抛出; 判断是否存在; 写入sql逻辑
                    CourseDO courseDO = CourseConvert.INSTANCE.convert(uploadCourse);
                    // 注意：这里的数据库操作可能需要单独的事务管理，如果需要的话
                    courseMapper.insert(courseDO);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
            try {
                if (!threadPoolExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                    threadPoolExecutor.shutdownNow();
                    if (!threadPoolExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                        System.err.println("线程池中止");
                    }
                }
            } catch (InterruptedException ie) {
                threadPoolExecutor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }

        return courseUploadResVO;
    }

    /*
    * 导入课程学生表
    * */
    @Override
    @Transactional(rollbackFor = Exception.class) // 添加事务，异常则回滚所有导入
    public CourseUploadResVO uploadStuListExcel(List<StuListUploadExcelVO> uploadStuLists, String originalFilename){
        if (CollUtil.isEmpty(uploadStuLists)) throw exception(STULIST_IS_EMPTY);
        // 提取文件名
        String prefix = "学生名单-";
        String suffix = ".xlsx";

        String corePart = originalFilename.substring(prefix.length(), originalFilename.length() - suffix.length());
        Long courseCode = Long.parseLong(corePart.substring(0, 9)); // 假设课程编码长度为9
        String courseName = corePart.substring(9); // 课程名称紧跟在课程编码之后

        CourseUploadResVO courseUploadResVO = CourseUploadResVO.builder()
                .createCourseNames(new ArrayList<>())
                .updateCourseNames(new ArrayList<>())
                .failureCourseNames(new HashMap<>()).build();

        /*
        * 测试使用线程池来优化时间
        * */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                6,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(8),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy() // 设置饱和策略为CallerRunsPolicy
        );

        long startTime = System.currentTimeMillis(); // 获取结束时间

        // TODO: 暂时不需要校验和异常抛出; 判断是否存在; 写入sql逻辑
        try {
            uploadStuLists.forEach(uploadStuList -> {
                threadPoolExecutor.execute(() -> {
                    StudentDO studentDO = StudentConvert.INSTANCE.convert(uploadStuList);
                    CourseStudentDO courseStudentDO = CourseStudentConvert.INSTANCE.convert(uploadStuList);
                    if (studentMapper.selectById(uploadStuList.getStudentId()) == null) studentMapper.insert(studentDO);
                    // TODO: course-student表未判断是否多次导入
                    courseStudentDO.setCourseId(courseCode);
                    courseStudentMapper.insert(courseStudentDO);
                });
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // TODO: 需要判断对应的Mapper是否是线程安全的
            threadPoolExecutor.shutdown(); // Shutdown the pool once all tasks are submitted
            try {
                // 等待所有任务完成后再继续执行，如果你想无限期等待，可以使用Long.MAX_VALUE代替超时值
                if (!threadPoolExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                    // 如果任务在超时时间内没有完成，则取消当前执行的任务
                    threadPoolExecutor.shutdownNow();
                    // 等待任务响应被取消
                    if (!threadPoolExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                        System.err.println("Pool did not terminate");
                    }
                }
            } catch (InterruptedException ie) {
                // 如果当前线程也中断，则重新取消
                threadPoolExecutor.shutdownNow();
                // 保留中断状态
                Thread.currentThread().interrupt();
            }
        }

        long endTime = System.currentTimeMillis(); // 获取结束时间
        System.out.println("上传学生名单总耗时：" + (endTime - startTime) + "ms");

        return courseUploadResVO;
    }
}


