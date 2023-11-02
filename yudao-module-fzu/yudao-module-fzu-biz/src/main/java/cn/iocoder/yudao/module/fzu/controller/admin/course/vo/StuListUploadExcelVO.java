package cn.iocoder.yudao.module.fzu.controller.admin.course.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false)
public class StuListUploadExcelVO {

    @ExcelProperty("学号")
    private String studentId;

    @ExcelProperty("姓名")
    private String studentName;

    @ExcelProperty("院系名称")
    private String departmentName;

    @ExcelProperty("专业")
    private String major;

    @ExcelProperty("学生类别")
    private String studentCategory;

    @ExcelProperty("任课教师")
    private String teacher;
}
