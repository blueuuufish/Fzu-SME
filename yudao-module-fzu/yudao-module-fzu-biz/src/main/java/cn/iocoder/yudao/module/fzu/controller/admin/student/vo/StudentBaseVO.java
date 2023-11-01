package cn.iocoder.yudao.module.fzu.controller.admin.student.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

/**
 * 学生 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class StudentBaseVO {

    @Schema(description = "姓名", example = "芋艿")
    private String name;

    @Schema(description = "专业")
    private String major;

    @Schema(description = "学生类别")
    private Integer category;

}
