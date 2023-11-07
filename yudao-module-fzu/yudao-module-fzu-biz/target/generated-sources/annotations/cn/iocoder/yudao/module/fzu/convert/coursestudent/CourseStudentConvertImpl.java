package cn.iocoder.yudao.module.fzu.convert.coursestudent;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.fzu.controller.admin.course.vo.StuListUploadExcelVO;
import cn.iocoder.yudao.module.fzu.controller.admin.coursestudent.vo.CourseStudentCreateReqVO;
import cn.iocoder.yudao.module.fzu.controller.admin.coursestudent.vo.CourseStudentExcelVO;
import cn.iocoder.yudao.module.fzu.controller.admin.coursestudent.vo.CourseStudentRespVO;
import cn.iocoder.yudao.module.fzu.controller.admin.coursestudent.vo.CourseStudentUpdateReqVO;
import cn.iocoder.yudao.module.fzu.dal.dataobject.coursestudent.CourseStudentDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-06T09:31:45+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
public class CourseStudentConvertImpl implements CourseStudentConvert {

    @Override
    public CourseStudentDO convert(CourseStudentCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        CourseStudentDO.CourseStudentDOBuilder courseStudentDO = CourseStudentDO.builder();

        courseStudentDO.courseId( bean.getCourseId() );
        courseStudentDO.studentId( bean.getStudentId() );

        return courseStudentDO.build();
    }

    @Override
    public CourseStudentDO convert(CourseStudentUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        CourseStudentDO.CourseStudentDOBuilder courseStudentDO = CourseStudentDO.builder();

        courseStudentDO.courseId( bean.getCourseId() );
        courseStudentDO.studentId( bean.getStudentId() );
        courseStudentDO.id( bean.getId() );

        return courseStudentDO.build();
    }

    @Override
    public CourseStudentRespVO convert(CourseStudentDO bean) {
        if ( bean == null ) {
            return null;
        }

        CourseStudentRespVO courseStudentRespVO = new CourseStudentRespVO();

        courseStudentRespVO.setCourseId( bean.getCourseId() );
        courseStudentRespVO.setStudentId( bean.getStudentId() );
        courseStudentRespVO.setId( bean.getId() );

        return courseStudentRespVO;
    }

    @Override
    public List<CourseStudentRespVO> convertList(List<CourseStudentDO> list) {
        if ( list == null ) {
            return null;
        }

        List<CourseStudentRespVO> list1 = new ArrayList<CourseStudentRespVO>( list.size() );
        for ( CourseStudentDO courseStudentDO : list ) {
            list1.add( convert( courseStudentDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<CourseStudentRespVO> convertPage(PageResult<CourseStudentDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<CourseStudentRespVO> pageResult = new PageResult<CourseStudentRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<CourseStudentExcelVO> convertList02(List<CourseStudentDO> list) {
        if ( list == null ) {
            return null;
        }

        List<CourseStudentExcelVO> list1 = new ArrayList<CourseStudentExcelVO>( list.size() );
        for ( CourseStudentDO courseStudentDO : list ) {
            list1.add( courseStudentDOToCourseStudentExcelVO( courseStudentDO ) );
        }

        return list1;
    }

    @Override
    public CourseStudentDO convert(StuListUploadExcelVO bean) {
        if ( bean == null ) {
            return null;
        }

        CourseStudentDO.CourseStudentDOBuilder courseStudentDO = CourseStudentDO.builder();

        if ( bean.getStudentId() != null ) {
            courseStudentDO.studentId( Long.parseLong( bean.getStudentId() ) );
        }

        return courseStudentDO.build();
    }

    protected CourseStudentExcelVO courseStudentDOToCourseStudentExcelVO(CourseStudentDO courseStudentDO) {
        if ( courseStudentDO == null ) {
            return null;
        }

        CourseStudentExcelVO courseStudentExcelVO = new CourseStudentExcelVO();

        courseStudentExcelVO.setCourseId( courseStudentDO.getCourseId() );
        courseStudentExcelVO.setStudentId( courseStudentDO.getStudentId() );
        courseStudentExcelVO.setId( courseStudentDO.getId() );

        return courseStudentExcelVO;
    }
}
