package cn.iocoder.yudao.module.fzu.service.coursestudent;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.fzu.controller.admin.coursestudent.vo.*;
import cn.iocoder.yudao.module.fzu.dal.dataobject.coursestudent.CourseStudentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 课程学生 Service 接口
 *
 * @author 芋道源码
 */
public interface CourseStudentService {

    /**
     * 创建课程学生
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createCourseStudent(@Valid CourseStudentCreateReqVO createReqVO);

    /**
     * 更新课程学生
     *
     * @param updateReqVO 更新信息
     */
    void updateCourseStudent(@Valid CourseStudentUpdateReqVO updateReqVO);

    /**
     * 删除课程学生
     *
     * @param id 编号
     */
    void deleteCourseStudent(Integer id);

    /**
     * 获得课程学生
     *
     * @param id 编号
     * @return 课程学生
     */
    CourseStudentDO getCourseStudent(Integer id);

    /**
     * 获得课程学生列表
     *
     * @param ids 编号
     * @return 课程学生列表
     */
    List<CourseStudentDO> getCourseStudentList(Collection<Integer> ids);

    /**
     * 获得课程学生分页
     *
     * @param pageReqVO 分页查询
     * @return 课程学生分页
     */
    PageResult<CourseStudentDO> getCourseStudentPage(CourseStudentPageReqVO pageReqVO);

    /**
     * 获得课程学生列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 课程学生列表
     */
    List<CourseStudentDO> getCourseStudentList(CourseStudentExportReqVO exportReqVO);

}
