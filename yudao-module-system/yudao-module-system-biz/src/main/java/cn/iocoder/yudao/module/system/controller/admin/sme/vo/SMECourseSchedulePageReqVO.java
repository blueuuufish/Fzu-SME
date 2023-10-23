package cn.iocoder.yudao.module.system.controller.admin.sme.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 排课分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SMECourseSchedulePageReqVO extends PageParam {

    @Schema(description = "课程id", example = "31376")
    private Integer courseId;

    @Schema(description = "学生id", example = "3802")
    private Integer stuId;

}
