package cn.iocoder.yudao.module.fzu.convert.course;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.fzu.controller.admin.course.vo.CourseCreateReqVO;
import cn.iocoder.yudao.module.fzu.controller.admin.course.vo.CourseExcelVO;
import cn.iocoder.yudao.module.fzu.controller.admin.course.vo.CourseRespVO;
import cn.iocoder.yudao.module.fzu.controller.admin.course.vo.CourseUpdateReqVO;
import cn.iocoder.yudao.module.fzu.controller.admin.course.vo.CourseUploadExcelVO;
import cn.iocoder.yudao.module.fzu.dal.dataobject.course.CourseDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-02T17:20:13+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
public class CourseConvertImpl implements CourseConvert {

    @Override
    public CourseDO convert(CourseCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        CourseDO.CourseDOBuilder courseDO = CourseDO.builder();

        courseDO.courseId( bean.getCourseId() );
        courseDO.name( bean.getName() );
        courseDO.term( bean.getTerm() );
        courseDO.classes( bean.getClasses() );
        courseDO.teacher( bean.getTeacher() );
        courseDO.week( bean.getWeek() );
        courseDO.startWeek( bean.getStartWeek() );
        courseDO.endWeek( bean.getEndWeek() );
        courseDO.startLesson( bean.getStartLesson() );
        courseDO.endLesson( bean.getEndLesson() );
        courseDO.location( bean.getLocation() );

        return courseDO.build();
    }

    @Override
    public CourseDO convert(CourseUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        CourseDO.CourseDOBuilder courseDO = CourseDO.builder();

        courseDO.courseId( bean.getCourseId() );
        courseDO.name( bean.getName() );
        courseDO.term( bean.getTerm() );
        courseDO.classes( bean.getClasses() );
        courseDO.teacher( bean.getTeacher() );
        courseDO.week( bean.getWeek() );
        courseDO.startWeek( bean.getStartWeek() );
        courseDO.endWeek( bean.getEndWeek() );
        courseDO.startLesson( bean.getStartLesson() );
        courseDO.endLesson( bean.getEndLesson() );
        courseDO.location( bean.getLocation() );
        courseDO.id( bean.getId() );

        return courseDO.build();
    }

    @Override
    public CourseRespVO convert(CourseDO bean) {
        if ( bean == null ) {
            return null;
        }

        CourseRespVO courseRespVO = new CourseRespVO();

        courseRespVO.setCourseId( bean.getCourseId() );
        courseRespVO.setName( bean.getName() );
        courseRespVO.setTerm( bean.getTerm() );
        courseRespVO.setClasses( bean.getClasses() );
        courseRespVO.setTeacher( bean.getTeacher() );
        courseRespVO.setWeek( bean.getWeek() );
        courseRespVO.setStartWeek( bean.getStartWeek() );
        courseRespVO.setEndWeek( bean.getEndWeek() );
        courseRespVO.setStartLesson( bean.getStartLesson() );
        courseRespVO.setEndLesson( bean.getEndLesson() );
        courseRespVO.setLocation( bean.getLocation() );
        courseRespVO.setId( bean.getId() );

        return courseRespVO;
    }

    @Override
    public List<CourseRespVO> convertList(List<CourseDO> list) {
        if ( list == null ) {
            return null;
        }

        List<CourseRespVO> list1 = new ArrayList<CourseRespVO>( list.size() );
        for ( CourseDO courseDO : list ) {
            list1.add( convert( courseDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<CourseRespVO> convertPage(PageResult<CourseDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<CourseRespVO> pageResult = new PageResult<CourseRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<CourseExcelVO> convertList02(List<CourseDO> list) {
        if ( list == null ) {
            return null;
        }

        List<CourseExcelVO> list1 = new ArrayList<CourseExcelVO>( list.size() );
        for ( CourseDO courseDO : list ) {
            list1.add( courseDOToCourseExcelVO( courseDO ) );
        }

        return list1;
    }

    @Override
    public CourseDO convert(CourseUploadExcelVO bean) {
        if ( bean == null ) {
            return null;
        }

        CourseDO.CourseDOBuilder courseDO = CourseDO.builder();

        if ( bean.getCourseId() != null ) {
            courseDO.courseId( Long.parseLong( bean.getCourseId() ) );
        }
        courseDO.name( bean.getName() );
        courseDO.term( bean.getTerm() );
        courseDO.classes( bean.getClasses() );
        courseDO.teacher( bean.getTeacher() );

        return courseDO.build();
    }

    protected CourseExcelVO courseDOToCourseExcelVO(CourseDO courseDO) {
        if ( courseDO == null ) {
            return null;
        }

        CourseExcelVO courseExcelVO = new CourseExcelVO();

        courseExcelVO.setCourseId( courseDO.getCourseId() );
        courseExcelVO.setName( courseDO.getName() );
        courseExcelVO.setTerm( courseDO.getTerm() );
        courseExcelVO.setClasses( courseDO.getClasses() );
        courseExcelVO.setTeacher( courseDO.getTeacher() );
        courseExcelVO.setWeek( courseDO.getWeek() );
        courseExcelVO.setStartWeek( courseDO.getStartWeek() );
        courseExcelVO.setEndWeek( courseDO.getEndWeek() );
        courseExcelVO.setStartLesson( courseDO.getStartLesson() );
        courseExcelVO.setEndLesson( courseDO.getEndLesson() );
        courseExcelVO.setLocation( courseDO.getLocation() );
        courseExcelVO.setId( courseDO.getId() );

        return courseExcelVO;
    }
}
