package cn.iocoder.yudao.module.system.controller.admin.sme.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 排课更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SMECourseScheduleUpdateReqVO extends SMECourseScheduleBaseVO {

    @Schema(description = "id号", requiredMode = Schema.RequiredMode.REQUIRED, example = "28813")
    @NotNull(message = "id号不能为空")
    private Long id;

    @Schema(description = "课程id", requiredMode = Schema.RequiredMode.REQUIRED, example = "31376")
    @NotNull(message = "课程id不能为空")
    private Integer courseId;

    @Schema(description = "学生id", requiredMode = Schema.RequiredMode.REQUIRED, example = "3802")
    @NotNull(message = "学生id不能为空")
    private Integer stuId;

}
