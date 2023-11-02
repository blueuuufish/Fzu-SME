package cn.iocoder.yudao.module.fzu.dal.dataobject.course;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 课程 DO
 *
 * @author 芋道源码
 */
@TableName("fzu_course")
@KeySequence("fzu_course_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
//@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDO {

    /**
     * 课程id
     */
    private Long courseId;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 学期
     */
    private String term;
    /**
     * 班级
     */
    private String classes;
    /**
     * 老师
     */
    private String teacher;
    /**
     * 周
     */
    private Integer week;
    /**
     * 开始周
     */
    private Integer startWeek;
    /**
     * 结束周
     */
    private Integer endWeek;
    /**
     * 开始节
     */
    private Integer startLesson;
    /**
     * 结束节
     */
    private Integer endLesson;
    /**
     * 地点
     */
    private String location;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

}
