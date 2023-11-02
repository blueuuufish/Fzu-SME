package cn.iocoder.yudao.module.fzu.controller.admin.course.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 导入 Response VO")
@Data
@Builder
public class CourseUploadResVO {

    @Schema(description = "创建成功的用户名数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> createCourseNames;

    @Schema(description = "更新成功的用户名数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> updateCourseNames;

    @Schema(description = "导入失败的用户集合，key 为用户名，value 为失败原因", requiredMode = Schema.RequiredMode.REQUIRED)
    private Map<String, String> failureCourseNames;

}
