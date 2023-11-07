package cn.iocoder.yudao.module.fzu.convert.coursestudent;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.fzu.controller.admin.course.vo.StuListUploadExcelVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.fzu.controller.admin.coursestudent.vo.*;
import cn.iocoder.yudao.module.fzu.dal.dataobject.coursestudent.CourseStudentDO;

/**
 * 课程学生 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface CourseStudentConvert {

    CourseStudentConvert INSTANCE = Mappers.getMapper(CourseStudentConvert.class);

    CourseStudentDO convert(CourseStudentCreateReqVO bean);

    CourseStudentDO convert(CourseStudentUpdateReqVO bean);

    CourseStudentRespVO convert(CourseStudentDO bean);

    List<CourseStudentRespVO> convertList(List<CourseStudentDO> list);

    PageResult<CourseStudentRespVO> convertPage(PageResult<CourseStudentDO> page);

    List<CourseStudentExcelVO> convertList02(List<CourseStudentDO> list);

    CourseStudentDO convert(StuListUploadExcelVO bean);
}
