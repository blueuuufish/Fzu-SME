package cn.iocoder.yudao.module.fzu.controller.admin.course.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 课程创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CourseCreateReqVO extends CourseBaseVO {

}
