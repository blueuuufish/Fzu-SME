package cn.iocoder.yudao.module.fzu.controller.admin.course.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 课程分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CoursePageReqVO extends PageParam {

    @Schema(description = "课程id", example = "27841")
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
