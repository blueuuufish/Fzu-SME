package cn.iocoder.yudao.module.fzu.convert.student;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.fzu.controller.admin.course.vo.StuListUploadExcelVO;
import cn.iocoder.yudao.module.fzu.controller.admin.student.vo.StudentCreateReqVO;
import cn.iocoder.yudao.module.fzu.controller.admin.student.vo.StudentExcelVO;
import cn.iocoder.yudao.module.fzu.controller.admin.student.vo.StudentRespVO;
import cn.iocoder.yudao.module.fzu.controller.admin.student.vo.StudentUpdateReqVO;
import cn.iocoder.yudao.module.fzu.dal.dataobject.student.StudentDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-06T10:15:22+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
public class StudentConvertImpl implements StudentConvert {

    @Override
    public StudentDO convert(StudentCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        StudentDO.StudentDOBuilder studentDO = StudentDO.builder();

        studentDO.name( bean.getName() );
        studentDO.major( bean.getMajor() );
        studentDO.category( bean.getCategory() );

        return studentDO.build();
    }

    @Override
    public StudentDO convert(StudentUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        StudentDO.StudentDOBuilder studentDO = StudentDO.builder();

        studentDO.studentId( bean.getStudentId() );
        studentDO.name( bean.getName() );
        studentDO.major( bean.getMajor() );
        studentDO.category( bean.getCategory() );

        return studentDO.build();
    }

    @Override
    public StudentRespVO convert(StudentDO bean) {
        if ( bean == null ) {
            return null;
        }

        StudentRespVO studentRespVO = new StudentRespVO();

        studentRespVO.setName( bean.getName() );
        studentRespVO.setMajor( bean.getMajor() );
        studentRespVO.setCategory( bean.getCategory() );
        studentRespVO.setStudentId( bean.getStudentId() );

        return studentRespVO;
    }

    @Override
    public List<StudentRespVO> convertList(List<StudentDO> list) {
        if ( list == null ) {
            return null;
        }

        List<StudentRespVO> list1 = new ArrayList<StudentRespVO>( list.size() );
        for ( StudentDO studentDO : list ) {
            list1.add( convert( studentDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<StudentRespVO> convertPage(PageResult<StudentDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<StudentRespVO> pageResult = new PageResult<StudentRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<StudentExcelVO> convertList02(List<StudentDO> list) {
        if ( list == null ) {
            return null;
        }

        List<StudentExcelVO> list1 = new ArrayList<StudentExcelVO>( list.size() );
        for ( StudentDO studentDO : list ) {
            list1.add( studentDOToStudentExcelVO( studentDO ) );
        }

        return list1;
    }

    @Override
    public StudentDO convert(StuListUploadExcelVO bean) {
        if ( bean == null ) {
            return null;
        }

        StudentDO.StudentDOBuilder studentDO = StudentDO.builder();

        studentDO.name( bean.getName() );
        studentDO.category( stringToInteger( bean.getCategory() ) );
        if ( bean.getStudentId() != null ) {
            studentDO.studentId( Long.parseLong( bean.getStudentId() ) );
        }
        studentDO.major( bean.getMajor() );

        return studentDO.build();
    }

    protected StudentExcelVO studentDOToStudentExcelVO(StudentDO studentDO) {
        if ( studentDO == null ) {
            return null;
        }

        StudentExcelVO studentExcelVO = new StudentExcelVO();

        studentExcelVO.setStudentId( studentDO.getStudentId() );
        studentExcelVO.setName( studentDO.getName() );
        studentExcelVO.setMajor( studentDO.getMajor() );
        studentExcelVO.setCategory( studentDO.getCategory() );

        return studentExcelVO;
    }
}
