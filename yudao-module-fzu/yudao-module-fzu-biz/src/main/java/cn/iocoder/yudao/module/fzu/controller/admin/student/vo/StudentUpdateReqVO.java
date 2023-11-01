package cn.iocoder.yudao.module.fzu.controller.admin.student.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 学生更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StudentUpdateReqVO extends StudentBaseVO {

    @Schema(description = "学号", requiredMode = Schema.RequiredMode.REQUIRED, example = "9196")
    @NotNull(message = "学号不能为空")
    private Long studentId;

}
