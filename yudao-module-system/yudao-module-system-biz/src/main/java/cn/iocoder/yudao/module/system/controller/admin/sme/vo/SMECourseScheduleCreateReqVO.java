package cn.iocoder.yudao.module.system.controller.admin.sme.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 排课创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SMECourseScheduleCreateReqVO extends SMECourseScheduleBaseVO {

    @Schema(description = "课程id", requiredMode = Schema.RequiredMode.REQUIRED, example = "31376")
    @NotNull(message = "课程id不能为空")
    private Integer courseId;

    @Schema(description = "学生id", requiredMode = Schema.RequiredMode.REQUIRED, example = "3802")
    @NotNull(message = "学生id不能为空")
    private Integer stuId;

}
