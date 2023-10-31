package cn.iocoder.yudao.module.system.service.course;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import jakarta.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.system.controller.admin.course.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.course.CourseDO;
import cn.iocoder.yudao.module.system.dal.mysql.course.CourseMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link CourseServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(CourseServiceImpl.class)
public class CourseServiceImplTest extends BaseDbUnitTest {

    @Resource
    private CourseServiceImpl courseService;

    @Resource
    private CourseMapper courseMapper;

    @Test
    public void testCreateCourse_success() {
        // 准备参数
        CourseCreateReqVO reqVO = randomPojo(CourseCreateReqVO.class);

        // 调用
        String courseId = courseService.createCourse(reqVO);
        // 断言
        assertNotNull(courseId);
        // 校验记录的属性是否正确
        CourseDO course = courseMapper.selectById(courseId);
        assertPojoEquals(reqVO, course);
    }

    @Test
    public void testUpdateCourse_success() {
        // mock 数据
        CourseDO dbCourse = randomPojo(CourseDO.class);
        courseMapper.insert(dbCourse);// @Sql: 先插入出一条存在的数据
        // 准备参数
        CourseUpdateReqVO reqVO = randomPojo(CourseUpdateReqVO.class, o -> {
            o.setId(dbCourse.getId()); // 设置更新的 ID
        });

        // 调用
        courseService.updateCourse(reqVO);
        // 校验是否更新正确
        CourseDO course = courseMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, course);
    }

    @Test
    public void testUpdateCourse_notExists() {
        // 准备参数
        CourseUpdateReqVO reqVO = randomPojo(CourseUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> courseService.updateCourse(reqVO), COURSE_NOT_EXISTS);
    }

    @Test
    public void testDeleteCourse_success() {
        // mock 数据
        CourseDO dbCourse = randomPojo(CourseDO.class);
        courseMapper.insert(dbCourse);// @Sql: 先插入出一条存在的数据
        // 准备参数
        String id = dbCourse.getId();

        // 调用
        courseService.deleteCourse(id);
       // 校验数据不存在了
       assertNull(courseMapper.selectById(id));
    }

    @Test
    public void testDeleteCourse_notExists() {
        // 准备参数
        String id = randomStringId();

        // 调用, 并断言异常
        assertServiceException(() -> courseService.deleteCourse(id), COURSE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCoursePage() {
       // mock 数据
       CourseDO dbCourse = randomPojo(CourseDO.class, o -> { // 等会查询到
           o.setCourseId(null);
           o.setName(null);
           o.setTerm(null);
           o.setClasses(null);
           o.setTeacher(null);
           o.setWeek(null);
           o.setStartWeek(null);
           o.setEndWeek(null);
           o.setStartLesson(null);
           o.setEndLesson(null);
           o.setLocation(null);
       });
       courseMapper.insert(dbCourse);
       // 测试 courseId 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setCourseId(null)));
       // 测试 name 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setName(null)));
       // 测试 term 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setTerm(null)));
       // 测试 classes 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setClasses(null)));
       // 测试 teacher 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setTeacher(null)));
       // 测试 week 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setWeek(null)));
       // 测试 startWeek 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setStartWeek(null)));
       // 测试 endWeek 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setEndWeek(null)));
       // 测试 startLesson 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setStartLesson(null)));
       // 测试 endLesson 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setEndLesson(null)));
       // 测试 location 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setLocation(null)));
       // 准备参数
       CoursePageReqVO reqVO = new CoursePageReqVO();
       reqVO.setCourseId(null);
       reqVO.setName(null);
       reqVO.setTerm(null);
       reqVO.setClasses(null);
       reqVO.setTeacher(null);
       reqVO.setWeek(null);
       reqVO.setStartWeek(null);
       reqVO.setEndWeek(null);
       reqVO.setStartLesson(null);
       reqVO.setEndLesson(null);
       reqVO.setLocation(null);

       // 调用
       PageResult<CourseDO> pageResult = courseService.getCoursePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbCourse, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCourseList() {
       // mock 数据
       CourseDO dbCourse = randomPojo(CourseDO.class, o -> { // 等会查询到
           o.setCourseId(null);
           o.setName(null);
           o.setTerm(null);
           o.setClasses(null);
           o.setTeacher(null);
           o.setWeek(null);
           o.setStartWeek(null);
           o.setEndWeek(null);
           o.setStartLesson(null);
           o.setEndLesson(null);
           o.setLocation(null);
       });
       courseMapper.insert(dbCourse);
       // 测试 courseId 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setCourseId(null)));
       // 测试 name 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setName(null)));
       // 测试 term 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setTerm(null)));
       // 测试 classes 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setClasses(null)));
       // 测试 teacher 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setTeacher(null)));
       // 测试 week 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setWeek(null)));
       // 测试 startWeek 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setStartWeek(null)));
       // 测试 endWeek 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setEndWeek(null)));
       // 测试 startLesson 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setStartLesson(null)));
       // 测试 endLesson 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setEndLesson(null)));
       // 测试 location 不匹配
       courseMapper.insert(cloneIgnoreId(dbCourse, o -> o.setLocation(null)));
       // 准备参数
       CourseExportReqVO reqVO = new CourseExportReqVO();
       reqVO.setCourseId(null);
       reqVO.setName(null);
       reqVO.setTerm(null);
       reqVO.setClasses(null);
       reqVO.setTeacher(null);
       reqVO.setWeek(null);
       reqVO.setStartWeek(null);
       reqVO.setEndWeek(null);
       reqVO.setStartLesson(null);
       reqVO.setEndLesson(null);
       reqVO.setLocation(null);

       // 调用
       List<CourseDO> list = courseService.getCourseList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbCourse, list.get(0));
    }

}
