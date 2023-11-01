package cn.iocoder.yudao.module.fzu.controller.admin.student.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 学生 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StudentRespVO extends StudentBaseVO {

    @Schema(description = "学号", requiredMode = Schema.RequiredMode.REQUIRED, example = "9196")
    private Long studentId;

}
