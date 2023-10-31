package cn.iocoder.yudao.module.fzu.dal.mysql.course;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.fzu.dal.dataobject.course.CourseDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.fzu.controller.admin.course.vo.*;

/**
 * 课程 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CourseMapper extends BaseMapperX<CourseDO> {

    default PageResult<CourseDO> selectPage(CoursePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CourseDO>()
                .eqIfPresent(CourseDO::getCourseId, reqVO.getCourseId())
                .likeIfPresent(CourseDO::getName, reqVO.getName())
                .eqIfPresent(CourseDO::getTerm, reqVO.getTerm())
                .eqIfPresent(CourseDO::getClasses, reqVO.getClasses())
                .likeIfPresent(CourseDO::getTeacher, reqVO.getTeacher())
                .eqIfPresent(CourseDO::getWeek, reqVO.getWeek())
                .eqIfPresent(CourseDO::getStartWeek, reqVO.getStartWeek())
                .eqIfPresent(CourseDO::getEndWeek, reqVO.getEndWeek())
                .eqIfPresent(CourseDO::getStartLesson, reqVO.getStartLesson())
                .eqIfPresent(CourseDO::getEndLesson, reqVO.getEndLesson())
                .likeIfPresent(CourseDO::getLocation, reqVO.getLocation())
                .orderByDesc(CourseDO::getId));
    }

    default List<CourseDO> selectList(CourseExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CourseDO>()
                .eqIfPresent(CourseDO::getCourseId, reqVO.getCourseId())
                .likeIfPresent(CourseDO::getName, reqVO.getName())
                .eqIfPresent(CourseDO::getTerm, reqVO.getTerm())
                .eqIfPresent(CourseDO::getClasses, reqVO.getClasses())
                .likeIfPresent(CourseDO::getTeacher, reqVO.getTeacher())
                .eqIfPresent(CourseDO::getWeek, reqVO.getWeek())
                .eqIfPresent(CourseDO::getStartWeek, reqVO.getStartWeek())
                .eqIfPresent(CourseDO::getEndWeek, reqVO.getEndWeek())
                .eqIfPresent(CourseDO::getStartLesson, reqVO.getStartLesson())
                .eqIfPresent(CourseDO::getEndLesson, reqVO.getEndLesson())
                .likeIfPresent(CourseDO::getLocation, reqVO.getLocation())
                .orderByDesc(CourseDO::getId));
    }

}
