package cn.iocoder.yudao.module.system.controller.admin.sme.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 排课 Excel 导出 Request VO，参数和 SMECourseSchedulePageReqVO 是一致的")
@Data
public class SMECourseScheduleExportReqVO {

    @Schema(description = "课程id", example = "31376")
    private Integer courseId;

    @Schema(description = "学生id", example = "3802")
    private Integer stuId;

}
