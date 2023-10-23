package cn.iocoder.yudao.module.system.controller.admin.sme;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.system.controller.admin.sme.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.sme.SMECourseScheduleDO;
import cn.iocoder.yudao.module.system.convert.sme.SMECourseScheduleConvert;
import cn.iocoder.yudao.module.system.service.sme.SMECourseScheduleService;

@Tag(name = "管理后台 - 排课")
@RestController
@RequestMapping("/system/SME-course-schedule")
@Validated
public class SMECourseScheduleController {

    @Resource
    private SMECourseScheduleService sMECourseScheduleService;

    @PostMapping("/create")
    @Operation(summary = "创建排课")
    @PreAuthorize("@ss.hasPermission('system:SME-course-schedule:create')")
    public CommonResult<Long> createSMECourseSchedule(@Valid @RequestBody SMECourseScheduleCreateReqVO createReqVO) {
        return success(sMECourseScheduleService.createSMECourseSchedule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新排课")
    @PreAuthorize("@ss.hasPermission('system:SME-course-schedule:update')")
    public CommonResult<Boolean> updateSMECourseSchedule(@Valid @RequestBody SMECourseScheduleUpdateReqVO updateReqVO) {
        sMECourseScheduleService.updateSMECourseSchedule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除排课")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:SME-course-schedule:delete')")
    public CommonResult<Boolean> deleteSMECourseSchedule(@RequestParam("id") Long id) {
        sMECourseScheduleService.deleteSMECourseSchedule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得排课")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:SME-course-schedule:query')")
    public CommonResult<SMECourseScheduleRespVO> getSMECourseSchedule(@RequestParam("id") Long id) {
        SMECourseScheduleDO sMECourseSchedule = sMECourseScheduleService.getSMECourseSchedule(id);
        return success(SMECourseScheduleConvert.INSTANCE.convert(sMECourseSchedule));
    }

    @GetMapping("/list")
    @Operation(summary = "获得排课列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('system:SME-course-schedule:query')")
    public CommonResult<List<SMECourseScheduleRespVO>> getSMECourseScheduleList(@RequestParam("ids") Collection<Long> ids) {
        List<SMECourseScheduleDO> list = sMECourseScheduleService.getSMECourseScheduleList(ids);
        return success(SMECourseScheduleConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得排课分页")
    @PreAuthorize("@ss.hasPermission('system:SME-course-schedule:query')")
    public CommonResult<PageResult<SMECourseScheduleRespVO>> getSMECourseSchedulePage(@Valid SMECourseSchedulePageReqVO pageVO) {
        PageResult<SMECourseScheduleDO> pageResult = sMECourseScheduleService.getSMECourseSchedulePage(pageVO);
        return success(SMECourseScheduleConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出排课 Excel")
    @PreAuthorize("@ss.hasPermission('system:SME-course-schedule:export')")
    @OperateLog(type = EXPORT)
    public void exportSMECourseScheduleExcel(@Valid SMECourseScheduleExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<SMECourseScheduleDO> list = sMECourseScheduleService.getSMECourseScheduleList(exportReqVO);
        // 导出 Excel
        List<SMECourseScheduleExcelVO> datas = SMECourseScheduleConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "排课.xls", "数据", SMECourseScheduleExcelVO.class, datas);
    }

}
