package cn.iocoder.yudao.module.fzu.dal.dataobject.student;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 学生 DO
 *
 * @author 芋道源码
 */
@TableName("fzu_student")
@KeySequence("fzu_student_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDO extends BaseDO {

    /**
     * 学号
     */
    @TableId
    private Long studentId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 专业
     */
    private String major;
    /**
     * 学生类别
     */
    private Integer category;

}
