package cn.iocoder.yudao.module.fzu.dal.mysql.coursestudent;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.fzu.dal.dataobject.coursestudent.CourseStudentDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.fzu.controller.admin.coursestudent.vo.*;

/**
 * 课程学生 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CourseStudentMapper extends BaseMapperX<CourseStudentDO> {

    default PageResult<CourseStudentDO> selectPage(CourseStudentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CourseStudentDO>()
                .eqIfPresent(CourseStudentDO::getCourseId, reqVO.getCourseId())
                .eqIfPresent(CourseStudentDO::getStudentId, reqVO.getStudentId())
                .orderByDesc(CourseStudentDO::getId));
    }

    default List<CourseStudentDO> selectList(CourseStudentExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CourseStudentDO>()
                .eqIfPresent(CourseStudentDO::getCourseId, reqVO.getCourseId())
                .eqIfPresent(CourseStudentDO::getStudentId, reqVO.getStudentId())
                .orderByDesc(CourseStudentDO::getId));
    }

}
