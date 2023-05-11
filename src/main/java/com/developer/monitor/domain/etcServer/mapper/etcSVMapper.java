package com.developer.monitor.domain.etcServer.mapper;

import com.developer.monitor.domain.etcServer.model.MInsertEtcSVDiskUsage;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVMain;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVProcChk;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface etcSVMapper {

    /**
     * @insertEtcSVMainData
     * @insetEtcSVDiskData
     * @insertEtcSVProcessData
     */

    public int insertEtcSVMainData(MInsertEtcSVMain mInsertEtcSVMain) throws Exception;
    public int insertEtcSVDiskData(MInsertEtcSVDiskUsage mInsertEtcSVDiskUsage) throws Exception;
    public int insertEtcSVProcessData(MInsertEtcSVProcChk mInsertEtcSVProcChk) throws Exception;
}
