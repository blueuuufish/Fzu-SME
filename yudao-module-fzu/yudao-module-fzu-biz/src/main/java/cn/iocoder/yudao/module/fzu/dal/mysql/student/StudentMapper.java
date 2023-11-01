package cn.iocoder.yudao.module.fzu.dal.mysql.student;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.fzu.dal.dataobject.student.StudentDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.fzu.controller.admin.student.vo.*;

/**
 * 学生 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface StudentMapper extends BaseMapperX<StudentDO> {

    default PageResult<StudentDO> selectPage(StudentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StudentDO>()
                .likeIfPresent(StudentDO::getName, reqVO.getName())
                .likeIfPresent(StudentDO::getMajor, reqVO.getMajor())
                .eqIfPresent(StudentDO::getCategory, reqVO.getCategory())
                .orderByDesc(StudentDO::getStudentId));
    }

    default List<StudentDO> selectList(StudentExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<StudentDO>()
                .likeIfPresent(StudentDO::getName, reqVO.getName())
                .likeIfPresent(StudentDO::getMajor, reqVO.getMajor())
                .eqIfPresent(StudentDO::getCategory, reqVO.getCategory())
                .orderByDesc(StudentDO::getStudentId));
    }

}
