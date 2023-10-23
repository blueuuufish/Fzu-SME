package cn.iocoder.yudao.module.system.service.sme;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.system.controller.admin.sme.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.sme.SMECourseScheduleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.system.convert.sme.SMECourseScheduleConvert;
import cn.iocoder.yudao.module.system.dal.mysql.sme.SMECourseScheduleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 排课 Service 实现类
 *
 * @author FZU
 */
@Service
@Validated
public class SMECourseScheduleServiceImpl implements SMECourseScheduleService {

    @Resource
    private SMECourseScheduleMapper sMECourseScheduleMapper;

    @Override
    public Long createSMECourseSchedule(SMECourseScheduleCreateReqVO createReqVO) {
        // 插入
        SMECourseScheduleDO sMECourseSchedule = SMECourseScheduleConvert.INSTANCE.convert(createReqVO);
        sMECourseScheduleMapper.insert(sMECourseSchedule);
        // 返回
        return sMECourseSchedule.getId();
    }

    @Override
    public void updateSMECourseSchedule(SMECourseScheduleUpdateReqVO updateReqVO) {
        // 校验存在
        validateSMECourseScheduleExists(updateReqVO.getId());
        // 更新
        SMECourseScheduleDO updateObj = SMECourseScheduleConvert.INSTANCE.convert(updateReqVO);
        sMECourseScheduleMapper.updateById(updateObj);
    }

    @Override
    public void deleteSMECourseSchedule(Long id) {
        // 校验存在
        validateSMECourseScheduleExists(id);
        // 删除
        sMECourseScheduleMapper.deleteById(id);
    }

    private void validateSMECourseScheduleExists(Long id) {
        if (sMECourseScheduleMapper.selectById(id) == null) {
            throw exception(SME_COURSE_SCHEDULE_NOT_EXISTS);
        }
    }

    @Override
    public SMECourseScheduleDO getSMECourseSchedule(Long id) {
        return sMECourseScheduleMapper.selectById(id);
    }

    @Override
    public List<SMECourseScheduleDO> getSMECourseScheduleList(Collection<Long> ids) {
        return sMECourseScheduleMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<SMECourseScheduleDO> getSMECourseSchedulePage(SMECourseSchedulePageReqVO pageReqVO) {
        return sMECourseScheduleMapper.selectPage(pageReqVO);
    }

    @Override
    public List<SMECourseScheduleDO> getSMECourseScheduleList(SMECourseScheduleExportReqVO exportReqVO) {
        return sMECourseScheduleMapper.selectList(exportReqVO);
    }

}
