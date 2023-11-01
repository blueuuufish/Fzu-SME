package cn.iocoder.yudao.module.fzu.controller.admin.coursestudent.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

/**
 * 课程学生 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CourseStudentBaseVO {

    @Schema(description = "课程id", example = "17938")
    private Long courseId;

    @Schema(description = "学生id", example = "13403")
    private Long studentId;

}
