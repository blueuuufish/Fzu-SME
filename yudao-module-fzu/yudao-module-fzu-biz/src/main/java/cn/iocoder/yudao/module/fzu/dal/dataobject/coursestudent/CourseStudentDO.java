package cn.iocoder.yudao.module.fzu.dal.dataobject.coursestudent;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 课程学生 DO
 *
 * @author 芋道源码
 */
@TableName("fzu_course_student")
@KeySequence("fzu_course_student_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseStudentDO extends BaseDO {

    /**
     * 课程id
     */
    private Long courseId;
    /**
     * 学生id
     */
    private Long studentId;
    /**
     * 主键id
     */
    @TableId
    private Integer id;

}
