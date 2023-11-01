package cn.iocoder.yudao.module.fzu.controller.admin.student.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 学生 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class StudentExcelVO {

    @ExcelProperty("学号")
    private Long studentId;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("专业")
    private String major;

    @ExcelProperty("学生类别")
    private Integer category;

}
