package cn.iocoder.yudao.module.infra.convert.db;

import cn.iocoder.yudao.module.infra.controller.admin.db.vo.DataSourceConfigCreateReqVO;
import cn.iocoder.yudao.module.infra.controller.admin.db.vo.DataSourceConfigRespVO;
import cn.iocoder.yudao.module.infra.controller.admin.db.vo.DataSourceConfigUpdateReqVO;
import cn.iocoder.yudao.module.infra.dal.dataobject.db.DataSourceConfigDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-02T15:22:52+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
public class DataSourceConfigConvertImpl implements DataSourceConfigConvert {

    @Override
    public DataSourceConfigDO convert(DataSourceConfigCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        DataSourceConfigDO dataSourceConfigDO = new DataSourceConfigDO();

        dataSourceConfigDO.setName( bean.getName() );
        dataSourceConfigDO.setUrl( bean.getUrl() );
        dataSourceConfigDO.setUsername( bean.getUsername() );
        dataSourceConfigDO.setPassword( bean.getPassword() );

        return dataSourceConfigDO;
    }

    @Override
    public DataSourceConfigDO convert(DataSourceConfigUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        DataSourceConfigDO dataSourceConfigDO = new DataSourceConfigDO();

        dataSourceConfigDO.setId( bean.getId() );
        dataSourceConfigDO.setName( bean.getName() );
        dataSourceConfigDO.setUrl( bean.getUrl() );
        dataSourceConfigDO.setUsername( bean.getUsername() );
        dataSourceConfigDO.setPassword( bean.getPassword() );

        return dataSourceConfigDO;
    }

    @Override
    public DataSourceConfigRespVO convert(DataSourceConfigDO bean) {
        if ( bean == null ) {
            return null;
        }

        DataSourceConfigRespVO dataSourceConfigRespVO = new DataSourceConfigRespVO();

        dataSourceConfigRespVO.setName( bean.getName() );
        dataSourceConfigRespVO.setUrl( bean.getUrl() );
        dataSourceConfigRespVO.setUsername( bean.getUsername() );
        if ( bean.getId() != null ) {
            dataSourceConfigRespVO.setId( bean.getId().intValue() );
        }
        dataSourceConfigRespVO.setCreateTime( bean.getCreateTime() );

        return dataSourceConfigRespVO;
    }

    @Override
    public List<DataSourceConfigRespVO> convertList(List<DataSourceConfigDO> list) {
        if ( list == null ) {
            return null;
        }

        List<DataSourceConfigRespVO> list1 = new ArrayList<DataSourceConfigRespVO>( list.size() );
        for ( DataSourceConfigDO dataSourceConfigDO : list ) {
            list1.add( convert( dataSourceConfigDO ) );
        }

        return list1;
    }
}
