package cn.iocoder.yudao.module.fzu.service.course;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.fzu.controller.admin.course.vo.*;
import cn.iocoder.yudao.module.fzu.dal.dataobject.course.CourseDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 课程 Service 接口
 *
 * @author 芋道源码
 */
public interface CourseService {

    /**
     * 创建课程
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createCourse(@Valid CourseCreateReqVO createReqVO);

    /**
     * 更新课程
     *
     * @param updateReqVO 更新信息
     */
    void updateCourse(@Valid CourseUpdateReqVO updateReqVO);

    /**
     * 删除课程
     *
     * @param id 编号
     */
    void deleteCourse(Integer id);

    /**
     * 获得课程
     *
     * @param id 编号
     * @return 课程
     */
    CourseDO getCourse(Integer id);

    /**
     * 获得课程列表
     *
     * @param ids 编号
     * @return 课程列表
     */
    List<CourseDO> getCourseList(Collection<Integer> ids);

    /**
     * 获得课程分页
     *
     * @param pageReqVO 分页查询
     * @return 课程分页
     */
    PageResult<CourseDO> getCoursePage(CoursePageReqVO pageReqVO);

    /**
     * 获得课程列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 课程列表
     */
    List<CourseDO> getCourseList(CourseExportReqVO exportReqVO);

}
