package cn.iocoder.yudao.module.fzu.service.student;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.fzu.controller.admin.student.vo.*;
import cn.iocoder.yudao.module.fzu.dal.dataobject.student.StudentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 学生 Service 接口
 *
 * @author 芋道源码
 */
public interface StudentService {

    /**
     * 创建学生
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStudent(@Valid StudentCreateReqVO createReqVO);

    /**
     * 更新学生
     *
     * @param updateReqVO 更新信息
     */
    void updateStudent(@Valid StudentUpdateReqVO updateReqVO);

    /**
     * 删除学生
     *
     * @param id 编号
     */
    void deleteStudent(Long id);

    /**
     * 获得学生
     *
     * @param id 编号
     * @return 学生
     */
    StudentDO getStudent(Long id);

    /**
     * 获得学生列表
     *
     * @param ids 编号
     * @return 学生列表
     */
    List<StudentDO> getStudentList(Collection<Long> ids);

    /**
     * 获得学生分页
     *
     * @param pageReqVO 分页查询
     * @return 学生分页
     */
    PageResult<StudentDO> getStudentPage(StudentPageReqVO pageReqVO);

    /**
     * 获得学生列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 学生列表
     */
    List<StudentDO> getStudentList(StudentExportReqVO exportReqVO);

}
