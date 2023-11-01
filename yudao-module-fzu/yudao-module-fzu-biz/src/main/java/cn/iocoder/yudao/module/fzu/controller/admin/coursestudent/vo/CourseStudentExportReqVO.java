package cn.iocoder.yudao.module.fzu.controller.admin.coursestudent.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 课程学生 Excel 导出 Request VO，参数和 CourseStudentPageReqVO 是一致的")
@Data
public class CourseStudentExportReqVO {

    @Schema(description = "课程id", example = "17938")
    private Long courseId;

    @Schema(description = "学生id", example = "13403")
    private Long studentId;

}
