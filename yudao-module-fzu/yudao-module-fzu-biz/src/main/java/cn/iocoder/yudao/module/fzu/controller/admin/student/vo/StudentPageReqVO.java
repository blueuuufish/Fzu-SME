package cn.iocoder.yudao.module.fzu.controller.admin.student.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 学生分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StudentPageReqVO extends PageParam {

    @Schema(description = "姓名", example = "芋艿")
    private String name;

    @Schema(description = "专业")
    private String major;

    @Schema(description = "学生类别")
    private Integer category;

}
