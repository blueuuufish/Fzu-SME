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
public class CourseUploadExcelVO {

    @ExcelProperty("课程号")
    private String courseId;

    @ExcelProperty("课程名称")
    private String name;

    @ExcelProperty("学年学期")
    private String term;

    @ExcelProperty("班级")
    private String classes;

    @ExcelProperty("任课教师")
    private String teacher;
    // TODO:
}
