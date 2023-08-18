package com.developer.monitor.domain.etcServer.mapper;

import com.developer.monitor.domain.etcServer.model.MInsertEtcSVDiskUsage;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVMain;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVProcChk;

import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface etcSVMapper {

    void insertEtcSVMainData(MInsertEtcSVMain mInsertEtcSVMain);
    void insertEtcSVProcData(MInsertEtcSVProcChk mInsertEtcSVProcChk);
    void insertEtcSVDiskData(MInsertEtcSVDiskUsage mInsertEtcSVDiskUsage);
    public int insertEtcSVProcessData(MInsertEtcSVProcChk mInsertEtcSVProcChk) throws Exception;
}
