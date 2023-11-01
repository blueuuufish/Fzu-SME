package cn.iocoder.yudao.module.fzu.service.coursestudent;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.fzu.controller.admin.coursestudent.vo.*;
import cn.iocoder.yudao.module.fzu.dal.dataobject.coursestudent.CourseStudentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.fzu.convert.coursestudent.CourseStudentConvert;
import cn.iocoder.yudao.module.fzu.dal.mysql.coursestudent.CourseStudentMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.fzu.enums.ErrorCodeConstants.*;

/**
 * 课程学生 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CourseStudentServiceImpl implements CourseStudentService {

    @Resource
    private CourseStudentMapper courseStudentMapper;

    @Override
    public Integer createCourseStudent(CourseStudentCreateReqVO createReqVO) {
        // 插入
        CourseStudentDO courseStudent = CourseStudentConvert.INSTANCE.convert(createReqVO);
        courseStudentMapper.insert(courseStudent);
        // 返回
        return courseStudent.getId();
    }

    @Override
    public void updateCourseStudent(CourseStudentUpdateReqVO updateReqVO) {
        // 校验存在
        validateCourseStudentExists(updateReqVO.getId());
        // 更新
        CourseStudentDO updateObj = CourseStudentConvert.INSTANCE.convert(updateReqVO);
        courseStudentMapper.updateById(updateObj);
    }

    @Override
    public void deleteCourseStudent(Integer id) {
        // 校验存在
        validateCourseStudentExists(id);
        // 删除
        courseStudentMapper.deleteById(id);
    }

    private void validateCourseStudentExists(Integer id) {
        if (courseStudentMapper.selectById(id) == null) {
            throw exception(COURSE_STUDENT_NOT_EXISTS);
        }
    }

    @Override
    public CourseStudentDO getCourseStudent(Integer id) {
        return courseStudentMapper.selectById(id);
    }

    @Override
    public List<CourseStudentDO> getCourseStudentList(Collection<Integer> ids) {
        return courseStudentMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CourseStudentDO> getCourseStudentPage(CourseStudentPageReqVO pageReqVO) {
        return courseStudentMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CourseStudentDO> getCourseStudentList(CourseStudentExportReqVO exportReqVO) {
        return courseStudentMapper.selectList(exportReqVO);
    }

}
