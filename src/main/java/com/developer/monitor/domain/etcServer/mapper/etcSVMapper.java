package com.developer.monitor.domain.etcServer.mapper;

import com.developer.monitor.domain.etcServer.model.MInsertEtcSVDiskUsage;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVMain;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVProcChk;
import com.developer.monitor.domain.etcServer.model.MXmlGetEtcSVEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;


import java.util.Map;

@Mapper
public interface etcSVMapper {

    /**
     * @insertEtcSVMainData
     * @insetEtcSVDiskData
     * @insertEtcSVProcessData
     */

    void insertEtcSVMainData(MInsertEtcSVMain mInsertEtcSVMain) throws Exception;
    public int insertEtcSVDiskData(MInsertEtcSVDiskUsage mInsertEtcSVDiskUsage) throws Exception;
    public int insertEtcSVProcessData(MInsertEtcSVProcChk mInsertEtcSVProcChk) throws Exception;
}
