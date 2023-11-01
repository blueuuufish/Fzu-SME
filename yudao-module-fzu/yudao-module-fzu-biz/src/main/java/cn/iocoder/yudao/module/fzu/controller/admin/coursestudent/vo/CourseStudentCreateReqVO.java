package cn.iocoder.yudao.module.fzu.controller.admin.coursestudent.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 课程学生创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CourseStudentCreateReqVO extends CourseStudentBaseVO {

}
