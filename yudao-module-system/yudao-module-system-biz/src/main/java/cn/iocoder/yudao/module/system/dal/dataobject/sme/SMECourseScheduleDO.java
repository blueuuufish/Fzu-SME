package cn.iocoder.yudao.module.system.dal.dataobject.sme;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 排课 DO
 *
 * @author FZU
 */
@TableName("system_association")
@KeySequence("system_association_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SMECourseScheduleDO extends BaseDO {

    /**
     * id号
     */
    @TableId
    private Long id;
    /**
     * 课程id
     */
    private Integer courseId;
    /**
     * 学生id
     */
    private Integer stuId;

}
