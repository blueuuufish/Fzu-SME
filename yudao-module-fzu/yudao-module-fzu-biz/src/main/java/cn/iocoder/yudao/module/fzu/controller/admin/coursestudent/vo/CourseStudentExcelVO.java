package cn.iocoder.yudao.module.fzu.controller.admin.coursestudent.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 课程学生 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class CourseStudentExcelVO {

    @ExcelProperty("课程id")
    private Long courseId;

    @ExcelProperty("学生id")
    private Long studentId;

    @ExcelProperty("主键id")
    private Integer id;

}
