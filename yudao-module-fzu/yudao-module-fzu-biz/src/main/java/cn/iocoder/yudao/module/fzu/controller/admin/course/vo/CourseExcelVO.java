package cn.iocoder.yudao.module.fzu.controller.admin.course.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 课程 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class CourseExcelVO {

    @ExcelProperty("课程id")
    private Long courseId;

    @ExcelProperty("课程名称")
    private String name;

    @ExcelProperty("学期")
    private String term;

    @ExcelProperty("班级")
    private String classes;

    @ExcelProperty("老师")
    private String teacher;

    @ExcelProperty("周")
    private Integer week;

    @ExcelProperty("开始周")
    private Integer startWeek;

    @ExcelProperty("结束周")
    private Integer endWeek;

    @ExcelProperty("开始节")
    private Integer startLesson;

    @ExcelProperty("结束节")
    private Integer endLesson;

    @ExcelProperty("地点")
    private String location;

    @ExcelProperty("主键")
    private Integer id;

}
