package cn.iocoder.yudao.module.system.dal.mysql.sme;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.sme.SMECourseScheduleDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.sme.vo.*;

/**
 * 排课 Mapper
 *
 * @author FZU
 */
@Mapper
public interface SMECourseScheduleMapper extends BaseMapperX<SMECourseScheduleDO> {

    default PageResult<SMECourseScheduleDO> selectPage(SMECourseSchedulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SMECourseScheduleDO>()
                .eqIfPresent(SMECourseScheduleDO::getCourseId, reqVO.getCourseId())
                .eqIfPresent(SMECourseScheduleDO::getStuId, reqVO.getStuId())
                .orderByDesc(SMECourseScheduleDO::getId));
    }

    default List<SMECourseScheduleDO> selectList(SMECourseScheduleExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<SMECourseScheduleDO>()
                .eqIfPresent(SMECourseScheduleDO::getCourseId, reqVO.getCourseId())
                .eqIfPresent(SMECourseScheduleDO::getStuId, reqVO.getStuId())
                .orderByDesc(SMECourseScheduleDO::getId));
    }

}
