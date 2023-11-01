package cn.iocoder.yudao.module.fzu.controller.admin.coursestudent.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 课程学生更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CourseStudentUpdateReqVO extends CourseStudentBaseVO {

    @Schema(description = "主键id", requiredMode = Schema.RequiredMode.REQUIRED, example = "7016")
    @NotNull(message = "主键id不能为空")
    private Integer id;

}
