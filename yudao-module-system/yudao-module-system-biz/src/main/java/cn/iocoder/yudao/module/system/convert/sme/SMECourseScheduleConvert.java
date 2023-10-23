package cn.iocoder.yudao.module.system.convert.sme;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.system.controller.admin.sme.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.sme.SMECourseScheduleDO;

/**
 * 排课 Convert
 *
 * @author FZU
 */
@Mapper
public interface SMECourseScheduleConvert {

    SMECourseScheduleConvert INSTANCE = Mappers.getMapper(SMECourseScheduleConvert.class);

    SMECourseScheduleExcelVO convertToExcelVO(SMECourseScheduleDO bean);


    SMECourseScheduleDO convert(SMECourseScheduleCreateReqVO bean);

    SMECourseScheduleDO convert(SMECourseScheduleUpdateReqVO bean);

    SMECourseScheduleRespVO convert(SMECourseScheduleDO bean);

    List<SMECourseScheduleRespVO> convertList(List<SMECourseScheduleDO> list);

    PageResult<SMECourseScheduleRespVO> convertPage(PageResult<SMECourseScheduleDO> page);

    List<SMECourseScheduleExcelVO> convertList02(List<SMECourseScheduleDO> list);

}
