package cn.iocoder.yudao.module.fzu.controller.admin.course;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.fzu.controller.admin.course.vo.*;
import cn.iocoder.yudao.module.fzu.dal.dataobject.course.CourseDO;
import cn.iocoder.yudao.module.fzu.convert.course.CourseConvert;
import cn.iocoder.yudao.module.fzu.service.course.CourseService;

@Tag(name = "管理后台 - 课程")
@RestController
@RequestMapping("/fzu/course")
@Validated
public class CourseController {

    @Resource
    private CourseService courseService;

    @PostMapping("/create")
    @Operation(summary = "创建课程")
    @PreAuthorize("@ss.hasPermission('fzu:course:create')")
    public CommonResult<Integer> createCourse(@Valid @RequestBody CourseCreateReqVO createReqVO) {
        return success(courseService.createCourse(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新课程")
    @PreAuthorize("@ss.hasPermission('fzu:course:update')")
    public CommonResult<Boolean> updateCourse(@Valid @RequestBody CourseUpdateReqVO updateReqVO) {
        courseService.updateCourse(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除课程")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('fzu:course:delete')")
    public CommonResult<Boolean> deleteCourse(@RequestParam("id") Integer id) {
        courseService.deleteCourse(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得课程")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('fzu:course:query')")
    public CommonResult<CourseRespVO> getCourse(@RequestParam("id") Integer id) {
        CourseDO course = courseService.getCourse(id);
        return success(CourseConvert.INSTANCE.convert(course));
    }

    @GetMapping("/list")
    @Operation(summary = "获得课程列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('fzu:course:query')")
    public CommonResult<List<CourseRespVO>> getCourseList(@RequestParam("ids") Collection<Integer> ids) {
        List<CourseDO> list = courseService.getCourseList(ids);
        return success(CourseConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得课程分页")
    @PreAuthorize("@ss.hasPermission('fzu:course:query')")
    public CommonResult<PageResult<CourseRespVO>> getCoursePage(@Valid CoursePageReqVO pageVO) {
        PageResult<CourseDO> pageResult = courseService.getCoursePage(pageVO);
        return success(CourseConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出课程 Excel")
    @PreAuthorize("@ss.hasPermission('fzu:course:export')")
    @OperateLog(type = EXPORT)
    public void exportCourseExcel(@Valid CourseExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CourseDO> list = courseService.getCourseList(exportReqVO);
        // 导出 Excel
        List<CourseExcelVO> datas = CourseConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "课程.xls", "数据", CourseExcelVO.class, datas);
    }

}
