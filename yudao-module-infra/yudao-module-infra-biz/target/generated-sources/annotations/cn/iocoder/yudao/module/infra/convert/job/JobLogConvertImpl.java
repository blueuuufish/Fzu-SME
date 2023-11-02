package cn.iocoder.yudao.module.infra.convert.job;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.infra.controller.admin.job.vo.log.JobLogExcelVO;
import cn.iocoder.yudao.module.infra.controller.admin.job.vo.log.JobLogRespVO;
import cn.iocoder.yudao.module.infra.dal.dataobject.job.JobLogDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-02T15:22:53+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
public class JobLogConvertImpl implements JobLogConvert {

    @Override
    public JobLogRespVO convert(JobLogDO bean) {
        if ( bean == null ) {
            return null;
        }

        JobLogRespVO jobLogRespVO = new JobLogRespVO();

        jobLogRespVO.setJobId( bean.getJobId() );
        jobLogRespVO.setHandlerName( bean.getHandlerName() );
        jobLogRespVO.setHandlerParam( bean.getHandlerParam() );
        jobLogRespVO.setExecuteIndex( bean.getExecuteIndex() );
        jobLogRespVO.setBeginTime( bean.getBeginTime() );
        jobLogRespVO.setEndTime( bean.getEndTime() );
        jobLogRespVO.setDuration( bean.getDuration() );
        jobLogRespVO.setStatus( bean.getStatus() );
        jobLogRespVO.setResult( bean.getResult() );
        jobLogRespVO.setId( bean.getId() );
        jobLogRespVO.setCreateTime( bean.getCreateTime() );

        return jobLogRespVO;
    }

    @Override
    public List<JobLogRespVO> convertList(List<JobLogDO> list) {
        if ( list == null ) {
            return null;
        }

        List<JobLogRespVO> list1 = new ArrayList<JobLogRespVO>( list.size() );
        for ( JobLogDO jobLogDO : list ) {
            list1.add( convert( jobLogDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<JobLogRespVO> convertPage(PageResult<JobLogDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<JobLogRespVO> pageResult = new PageResult<JobLogRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<JobLogExcelVO> convertList02(List<JobLogDO> list) {
        if ( list == null ) {
            return null;
        }

        List<JobLogExcelVO> list1 = new ArrayList<JobLogExcelVO>( list.size() );
        for ( JobLogDO jobLogDO : list ) {
            list1.add( jobLogDOToJobLogExcelVO( jobLogDO ) );
        }

        return list1;
    }

    protected JobLogExcelVO jobLogDOToJobLogExcelVO(JobLogDO jobLogDO) {
        if ( jobLogDO == null ) {
            return null;
        }

        JobLogExcelVO jobLogExcelVO = new JobLogExcelVO();

        jobLogExcelVO.setId( jobLogDO.getId() );
        jobLogExcelVO.setJobId( jobLogDO.getJobId() );
        jobLogExcelVO.setHandlerName( jobLogDO.getHandlerName() );
        jobLogExcelVO.setHandlerParam( jobLogDO.getHandlerParam() );
        jobLogExcelVO.setExecuteIndex( jobLogDO.getExecuteIndex() );
        jobLogExcelVO.setBeginTime( jobLogDO.getBeginTime() );
        jobLogExcelVO.setEndTime( jobLogDO.getEndTime() );
        jobLogExcelVO.setDuration( jobLogDO.getDuration() );
        jobLogExcelVO.setStatus( jobLogDO.getStatus() );
        jobLogExcelVO.setResult( jobLogDO.getResult() );
        jobLogExcelVO.setCreateTime( jobLogDO.getCreateTime() );

        return jobLogExcelVO;
    }
}
