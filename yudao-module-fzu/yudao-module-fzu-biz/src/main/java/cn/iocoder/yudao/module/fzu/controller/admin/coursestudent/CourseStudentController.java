package cn.iocoder.yudao.module.fzu.controller.admin.coursestudent;

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

import cn.iocoder.yudao.module.fzu.controller.admin.coursestudent.vo.*;
import cn.iocoder.yudao.module.fzu.dal.dataobject.coursestudent.CourseStudentDO;
import cn.iocoder.yudao.module.fzu.convert.coursestudent.CourseStudentConvert;
import cn.iocoder.yudao.module.fzu.service.coursestudent.CourseStudentService;

@Tag(name = "管理后台 - 课程学生")
@RestController
@RequestMapping("/fzu/course-student")
@Validated
public class CourseStudentController {

    @Resource
    private CourseStudentService courseStudentService;

    @PostMapping("/create")
    @Operation(summary = "创建课程学生")
    @PreAuthorize("@ss.hasPermission('fzu:course-student:create')")
    public CommonResult<Integer> createCourseStudent(@Valid @RequestBody CourseStudentCreateReqVO createReqVO) {
        return success(courseStudentService.createCourseStudent(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新课程学生")
    @PreAuthorize("@ss.hasPermission('fzu:course-student:update')")
    public CommonResult<Boolean> updateCourseStudent(@Valid @RequestBody CourseStudentUpdateReqVO updateReqVO) {
        courseStudentService.updateCourseStudent(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除课程学生")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('fzu:course-student:delete')")
    public CommonResult<Boolean> deleteCourseStudent(@RequestParam("id") Integer id) {
        courseStudentService.deleteCourseStudent(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得课程学生")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('fzu:course-student:query')")
    public CommonResult<CourseStudentRespVO> getCourseStudent(@RequestParam("id") Integer id) {
        CourseStudentDO courseStudent = courseStudentService.getCourseStudent(id);
        return success(CourseStudentConvert.INSTANCE.convert(courseStudent));
    }

    @GetMapping("/list")
    @Operation(summary = "获得课程学生列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('fzu:course-student:query')")
    public CommonResult<List<CourseStudentRespVO>> getCourseStudentList(@RequestParam("ids") Collection<Integer> ids) {
        List<CourseStudentDO> list = courseStudentService.getCourseStudentList(ids);
        return success(CourseStudentConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得课程学生分页")
    @PreAuthorize("@ss.hasPermission('fzu:course-student:query')")
    public CommonResult<PageResult<CourseStudentRespVO>> getCourseStudentPage(@Valid CourseStudentPageReqVO pageVO) {
        PageResult<CourseStudentDO> pageResult = courseStudentService.getCourseStudentPage(pageVO);
        return success(CourseStudentConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出课程学生 Excel")
    @PreAuthorize("@ss.hasPermission('fzu:course-student:export')")
    @OperateLog(type = EXPORT)
    public void exportCourseStudentExcel(@Valid CourseStudentExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CourseStudentDO> list = courseStudentService.getCourseStudentList(exportReqVO);
        // 导出 Excel
        List<CourseStudentExcelVO> datas = CourseStudentConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "课程学生.xls", "数据", CourseStudentExcelVO.class, datas);
    }

}
