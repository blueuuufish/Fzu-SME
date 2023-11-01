package cn.iocoder.yudao.module.fzu.service.coursestudent;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import jakarta.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.fzu.controller.admin.coursestudent.vo.*;
import cn.iocoder.yudao.module.fzu.dal.dataobject.coursestudent.CourseStudentDO;
import cn.iocoder.yudao.module.fzu.dal.mysql.coursestudent.CourseStudentMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.fzu.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link CourseStudentServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(CourseStudentServiceImpl.class)
public class CourseStudentServiceImplTest extends BaseDbUnitTest {

    @Resource
    private CourseStudentServiceImpl courseStudentService;

    @Resource
    private CourseStudentMapper courseStudentMapper;

    @Test
    public void testCreateCourseStudent_success() {
        // 准备参数
        CourseStudentCreateReqVO reqVO = randomPojo(CourseStudentCreateReqVO.class);

        // 调用
        Integer courseStudentId = courseStudentService.createCourseStudent(reqVO);
        // 断言
        assertNotNull(courseStudentId);
        // 校验记录的属性是否正确
        CourseStudentDO courseStudent = courseStudentMapper.selectById(courseStudentId);
        assertPojoEquals(reqVO, courseStudent);
    }

    @Test
    public void testUpdateCourseStudent_success() {
        // mock 数据
        CourseStudentDO dbCourseStudent = randomPojo(CourseStudentDO.class);
        courseStudentMapper.insert(dbCourseStudent);// @Sql: 先插入出一条存在的数据
        // 准备参数
        CourseStudentUpdateReqVO reqVO = randomPojo(CourseStudentUpdateReqVO.class, o -> {
            o.setId(dbCourseStudent.getId()); // 设置更新的 ID
        });

        // 调用
        courseStudentService.updateCourseStudent(reqVO);
        // 校验是否更新正确
        CourseStudentDO courseStudent = courseStudentMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, courseStudent);
    }

    @Test
    public void testUpdateCourseStudent_notExists() {
        // 准备参数
        CourseStudentUpdateReqVO reqVO = randomPojo(CourseStudentUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> courseStudentService.updateCourseStudent(reqVO), COURSE_STUDENT_NOT_EXISTS);
    }

    @Test
    public void testDeleteCourseStudent_success() {
        // mock 数据
        CourseStudentDO dbCourseStudent = randomPojo(CourseStudentDO.class);
        courseStudentMapper.insert(dbCourseStudent);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Integer id = dbCourseStudent.getId();

        // 调用
        courseStudentService.deleteCourseStudent(id);
       // 校验数据不存在了
       assertNull(courseStudentMapper.selectById(id));
    }

    @Test
    public void testDeleteCourseStudent_notExists() {
        // 准备参数
        Integer id = randomIntegerId();

        // 调用, 并断言异常
        assertServiceException(() -> courseStudentService.deleteCourseStudent(id), COURSE_STUDENT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCourseStudentPage() {
       // mock 数据
       CourseStudentDO dbCourseStudent = randomPojo(CourseStudentDO.class, o -> { // 等会查询到
           o.setCourseId(null);
           o.setStudentId(null);
       });
       courseStudentMapper.insert(dbCourseStudent);
       // 测试 courseId 不匹配
       courseStudentMapper.insert(cloneIgnoreId(dbCourseStudent, o -> o.setCourseId(null)));
       // 测试 studentId 不匹配
       courseStudentMapper.insert(cloneIgnoreId(dbCourseStudent, o -> o.setStudentId(null)));
       // 准备参数
       CourseStudentPageReqVO reqVO = new CourseStudentPageReqVO();
       reqVO.setCourseId(null);
       reqVO.setStudentId(null);

       // 调用
       PageResult<CourseStudentDO> pageResult = courseStudentService.getCourseStudentPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbCourseStudent, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCourseStudentList() {
       // mock 数据
       CourseStudentDO dbCourseStudent = randomPojo(CourseStudentDO.class, o -> { // 等会查询到
           o.setCourseId(null);
           o.setStudentId(null);
       });
       courseStudentMapper.insert(dbCourseStudent);
       // 测试 courseId 不匹配
       courseStudentMapper.insert(cloneIgnoreId(dbCourseStudent, o -> o.setCourseId(null)));
       // 测试 studentId 不匹配
       courseStudentMapper.insert(cloneIgnoreId(dbCourseStudent, o -> o.setStudentId(null)));
       // 准备参数
       CourseStudentExportReqVO reqVO = new CourseStudentExportReqVO();
       reqVO.setCourseId(null);
       reqVO.setStudentId(null);

       // 调用
       List<CourseStudentDO> list = courseStudentService.getCourseStudentList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbCourseStudent, list.get(0));
    }

}
