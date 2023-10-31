package cn.iocoder.yudao.module.fzu.controller.admin.course.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 课程更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CourseUpdateReqVO extends CourseBaseVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "8859")
    @NotNull(message = "主键不能为空")
    private Integer id;

}
