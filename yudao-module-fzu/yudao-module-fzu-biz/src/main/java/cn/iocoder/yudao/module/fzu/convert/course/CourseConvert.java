package cn.iocoder.yudao.module.fzu.convert.course;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.fzu.controller.admin.course.vo.*;
import cn.iocoder.yudao.module.fzu.dal.dataobject.course.CourseDO;

/**
 * 课程 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface CourseConvert {

    CourseConvert INSTANCE = Mappers.getMapper(CourseConvert.class);

    CourseDO convert(CourseCreateReqVO bean);

    CourseDO convert(CourseUpdateReqVO bean);

    CourseRespVO convert(CourseDO bean);

    List<CourseRespVO> convertList(List<CourseDO> list);

    PageResult<CourseRespVO> convertPage(PageResult<CourseDO> page);

    List<CourseExcelVO> convertList02(List<CourseDO> list);

}
