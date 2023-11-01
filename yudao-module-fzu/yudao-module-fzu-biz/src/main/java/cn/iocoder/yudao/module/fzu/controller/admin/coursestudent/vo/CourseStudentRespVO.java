package cn.iocoder.yudao.module.fzu.controller.admin.coursestudent.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 课程学生 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CourseStudentRespVO extends CourseStudentBaseVO {

    @Schema(description = "主键id", requiredMode = Schema.RequiredMode.REQUIRED, example = "7016")
    private Integer id;

}
