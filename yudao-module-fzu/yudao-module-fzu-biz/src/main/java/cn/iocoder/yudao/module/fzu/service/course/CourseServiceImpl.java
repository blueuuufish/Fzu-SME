package cn.iocoder.yudao.module.fzu.service.course;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;
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

        handledList.forEach(uploadCourse -> {
            // TODO: 暂时不需要校验和异常抛出; 判断是否存在; 写入sql逻辑
            CourseDO courseDO = CourseConvert.INSTANCE.convert(uploadCourse);
            System.out.println("------------------->" + courseDO + "<-------------------");
            System.out.println("------------------->" + courseMapper.insert(courseDO) + "<-------------------");
        });
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
        if (originalFilename.startsWith(prefix) && originalFilename.endsWith(suffix)) {
            String corePart = originalFilename.substring(prefix.length(), originalFilename.length() - suffix.length());
            String courseCode = corePart.substring(0, 9); // 假设课程编码长度为9
            String courseName = corePart.substring(9); // 课程名称紧跟在课程编码之后
        }

        CourseUploadResVO courseUploadResVO = CourseUploadResVO.builder()
                .createCourseNames(new ArrayList<>())
                .updateCourseNames(new ArrayList<>())
                .failureCourseNames(new HashMap<>()).build();

        uploadStuLists.forEach(uploadStuList -> {
            // TODO: 暂时不需要校验和异常抛出; 判断是否存在; 写入sql逻辑

        });
        return courseUploadResVO;
    }
}


