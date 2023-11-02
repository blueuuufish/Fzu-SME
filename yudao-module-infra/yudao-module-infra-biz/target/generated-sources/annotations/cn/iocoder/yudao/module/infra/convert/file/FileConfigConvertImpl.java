package cn.iocoder.yudao.module.infra.convert.file;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.infra.controller.admin.file.vo.config.FileConfigCreateReqVO;
import cn.iocoder.yudao.module.infra.controller.admin.file.vo.config.FileConfigRespVO;
import cn.iocoder.yudao.module.infra.controller.admin.file.vo.config.FileConfigUpdateReqVO;
import cn.iocoder.yudao.module.infra.dal.dataobject.file.FileConfigDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-02T15:22:53+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
public class FileConfigConvertImpl implements FileConfigConvert {

    @Override
    public FileConfigDO convert(FileConfigCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        FileConfigDO.FileConfigDOBuilder fileConfigDO = FileConfigDO.builder();

        fileConfigDO.name( bean.getName() );
        fileConfigDO.storage( bean.getStorage() );
        fileConfigDO.remark( bean.getRemark() );

        return fileConfigDO.build();
    }

    @Override
    public FileConfigDO convert(FileConfigUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        FileConfigDO.FileConfigDOBuilder fileConfigDO = FileConfigDO.builder();

        fileConfigDO.id( bean.getId() );
        fileConfigDO.name( bean.getName() );
        fileConfigDO.remark( bean.getRemark() );

        return fileConfigDO.build();
    }

    @Override
    public FileConfigRespVO convert(FileConfigDO bean) {
        if ( bean == null ) {
            return null;
        }

        FileConfigRespVO fileConfigRespVO = new FileConfigRespVO();

        fileConfigRespVO.setName( bean.getName() );
        fileConfigRespVO.setRemark( bean.getRemark() );
        fileConfigRespVO.setId( bean.getId() );
        fileConfigRespVO.setStorage( bean.getStorage() );
        fileConfigRespVO.setMaster( bean.getMaster() );
        fileConfigRespVO.setConfig( bean.getConfig() );
        fileConfigRespVO.setCreateTime( bean.getCreateTime() );

        return fileConfigRespVO;
    }

    @Override
    public List<FileConfigRespVO> convertList(List<FileConfigDO> list) {
        if ( list == null ) {
            return null;
        }

        List<FileConfigRespVO> list1 = new ArrayList<FileConfigRespVO>( list.size() );
        for ( FileConfigDO fileConfigDO : list ) {
            list1.add( convert( fileConfigDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<FileConfigRespVO> convertPage(PageResult<FileConfigDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<FileConfigRespVO> pageResult = new PageResult<FileConfigRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }
}
