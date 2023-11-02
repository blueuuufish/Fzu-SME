package cn.iocoder.yudao.module.infra.convert.job;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.infra.controller.admin.job.vo.job.JobCreateReqVO;
import cn.iocoder.yudao.module.infra.controller.admin.job.vo.job.JobExcelVO;
import cn.iocoder.yudao.module.infra.controller.admin.job.vo.job.JobRespVO;
import cn.iocoder.yudao.module.infra.controller.admin.job.vo.job.JobUpdateReqVO;
import cn.iocoder.yudao.module.infra.dal.dataobject.job.JobDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-02T15:22:53+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
public class JobConvertImpl implements JobConvert {

    @Override
    public JobDO convert(JobCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        JobDO.JobDOBuilder jobDO = JobDO.builder();

        jobDO.name( bean.getName() );
        jobDO.handlerName( bean.getHandlerName() );
        jobDO.handlerParam( bean.getHandlerParam() );
        jobDO.cronExpression( bean.getCronExpression() );
        jobDO.retryCount( bean.getRetryCount() );
        jobDO.retryInterval( bean.getRetryInterval() );
        jobDO.monitorTimeout( bean.getMonitorTimeout() );

        return jobDO.build();
    }

    @Override
    public JobDO convert(JobUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        JobDO.JobDOBuilder jobDO = JobDO.builder();

        jobDO.id( bean.getId() );
        jobDO.name( bean.getName() );
        jobDO.handlerParam( bean.getHandlerParam() );
        jobDO.cronExpression( bean.getCronExpression() );
        jobDO.retryCount( bean.getRetryCount() );
        jobDO.retryInterval( bean.getRetryInterval() );
        jobDO.monitorTimeout( bean.getMonitorTimeout() );

        return jobDO.build();
    }

    @Override
    public JobRespVO convert(JobDO bean) {
        if ( bean == null ) {
            return null;
        }

        JobRespVO jobRespVO = new JobRespVO();

        jobRespVO.setName( bean.getName() );
        jobRespVO.setHandlerParam( bean.getHandlerParam() );
        jobRespVO.setCronExpression( bean.getCronExpression() );
        jobRespVO.setRetryCount( bean.getRetryCount() );
        jobRespVO.setRetryInterval( bean.getRetryInterval() );
        jobRespVO.setMonitorTimeout( bean.getMonitorTimeout() );
        jobRespVO.setId( bean.getId() );
        jobRespVO.setStatus( bean.getStatus() );
        jobRespVO.setHandlerName( bean.getHandlerName() );
        jobRespVO.setCreateTime( bean.getCreateTime() );

        return jobRespVO;
    }

    @Override
    public List<JobRespVO> convertList(List<JobDO> list) {
        if ( list == null ) {
            return null;
        }

        List<JobRespVO> list1 = new ArrayList<JobRespVO>( list.size() );
        for ( JobDO jobDO : list ) {
            list1.add( convert( jobDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<JobRespVO> convertPage(PageResult<JobDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<JobRespVO> pageResult = new PageResult<JobRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<JobExcelVO> convertList02(List<JobDO> list) {
        if ( list == null ) {
            return null;
        }

        List<JobExcelVO> list1 = new ArrayList<JobExcelVO>( list.size() );
        for ( JobDO jobDO : list ) {
            list1.add( jobDOToJobExcelVO( jobDO ) );
        }

        return list1;
    }

    protected JobExcelVO jobDOToJobExcelVO(JobDO jobDO) {
        if ( jobDO == null ) {
            return null;
        }

        JobExcelVO jobExcelVO = new JobExcelVO();

        jobExcelVO.setId( jobDO.getId() );
        jobExcelVO.setName( jobDO.getName() );
        jobExcelVO.setStatus( jobDO.getStatus() );
        jobExcelVO.setHandlerName( jobDO.getHandlerName() );
        jobExcelVO.setHandlerParam( jobDO.getHandlerParam() );
        jobExcelVO.setCronExpression( jobDO.getCronExpression() );
        jobExcelVO.setMonitorTimeout( jobDO.getMonitorTimeout() );
        jobExcelVO.setCreateTime( jobDO.getCreateTime() );

        return jobExcelVO;
    }
}
