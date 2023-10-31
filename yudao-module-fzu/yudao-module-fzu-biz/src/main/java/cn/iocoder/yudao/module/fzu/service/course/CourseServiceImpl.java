package cn.iocoder.yudao.module.fzu.service.course;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.fzu.controller.admin.course.vo.*;
import cn.iocoder.yudao.module.fzu.dal.dataobject.course.CourseDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.fzu.convert.course.CourseConvert;
import cn.iocoder.yudao.module.fzu.dal.mysql.course.CourseMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.fzu.enums.ErrorCodeConstants.*;

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

}
