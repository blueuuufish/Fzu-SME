package cn.iocoder.yudao.module.system.service.sme;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.sme.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.sme.SMECourseScheduleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 排课 Service 接口
 *
 * @author FZU
 */
public interface SMECourseScheduleService {

    /**
     * 创建排课
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSMECourseSchedule(@Valid SMECourseScheduleCreateReqVO createReqVO);

    /**
     * 更新排课
     *
     * @param updateReqVO 更新信息
     */
    void updateSMECourseSchedule(@Valid SMECourseScheduleUpdateReqVO updateReqVO);

    /**
     * 删除排课
     *
     * @param id 编号
     */
    void deleteSMECourseSchedule(Long id);

    /**
     * 获得排课
     *
     * @param id 编号
     * @return 排课
     */
    SMECourseScheduleDO getSMECourseSchedule(Long id);

    /**
     * 获得排课列表
     *
     * @param ids 编号
     * @return 排课列表
     */
    List<SMECourseScheduleDO> getSMECourseScheduleList(Collection<Long> ids);

    /**
     * 获得排课分页
     *
     * @param pageReqVO 分页查询
     * @return 排课分页
     */
    PageResult<SMECourseScheduleDO> getSMECourseSchedulePage(SMECourseSchedulePageReqVO pageReqVO);

    /**
     * 获得排课列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 排课列表
     */
    List<SMECourseScheduleDO> getSMECourseScheduleList(SMECourseScheduleExportReqVO exportReqVO);

}
