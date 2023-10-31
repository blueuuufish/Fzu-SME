package cn.iocoder.yudao.module.system.dal.dataobject.course;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 课程 DO
 *
 * @author 芋道源码
 */
@TableName("system_course")
@KeySequence("system_course_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDO extends BaseDO {

    /**
     * 课程id
     */
    private String courseId;
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
    private String week;
    /**
     * 开始周
     */
    private String startWeek;
    /**
     * 结束周
     */
    private String endWeek;
    /**
     * 开始节
     */
    private String startLesson;
    /**
     * 结束节
     */
    private String endLesson;
    /**
     * 地点
     */
    private String location;
    /**
     * 主键
     */
    @TableId(type = IdType.INPUT)
    private String id;

}
