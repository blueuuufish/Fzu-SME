package cn.iocoder.yudao.module.fzu.convert.student;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.fzu.controller.admin.course.vo.StuListUploadExcelVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.fzu.controller.admin.student.vo.*;
import cn.iocoder.yudao.module.fzu.dal.dataobject.student.StudentDO;

/**
 * 学生 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface StudentConvert {

    StudentConvert INSTANCE = Mappers.getMapper(StudentConvert.class);

    StudentDO convert(StudentCreateReqVO bean);

    StudentDO convert(StudentUpdateReqVO bean);

    StudentRespVO convert(StudentDO bean);

    List<StudentRespVO> convertList(List<StudentDO> list);

    PageResult<StudentRespVO> convertPage(PageResult<StudentDO> page);

    List<StudentExcelVO> convertList02(List<StudentDO> list);

    @Mapping(source = "name", target = "name")
    @Mapping(target = "category", source = "category", qualifiedByName = "stringToInteger")
    StudentDO convert(StuListUploadExcelVO bean);

    @Named("stringToInteger")
    default Integer stringToInteger(String category) {
        // 这里不需要捕获NumberFormatException，因为我们不是解析数字
        // 直接根据字符串内容返回对应的整数值
        return "学术型硕士".equals(category) ? 1 : 0;
    }
}
