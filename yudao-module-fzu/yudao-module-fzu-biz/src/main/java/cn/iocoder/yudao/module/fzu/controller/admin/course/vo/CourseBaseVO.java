package cn.iocoder.yudao.module.fzu.controller.admin.course.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

/**
 * 课程 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CourseBaseVO {

    @Schema(description = "课程id", requiredMode = Schema.RequiredMode.REQUIRED, example = "27841")
    @NotNull(message = "课程id不能为空")
    private Long courseId;

    @Schema(description = "课程名称", example = "张三")
    private String name;

    @Schema(description = "学期")
    private String term;

    @Schema(description = "班级")
    private String classes;

    @Schema(description = "老师")
    private String teacher;

    @Schema(description = "周")
    private Integer week;

    @Schema(description = "开始周")
    private Integer startWeek;

    @Schema(description = "结束周")
    private Integer endWeek;

    @Schema(description = "开始节")
    private Integer startLesson;

    @Schema(description = "结束节")
    private Integer endLesson;

    @Schema(description = "地点")
    private String location;

}
