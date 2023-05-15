package com.developer.monitor.domain.etcServer.mapper;

import com.developer.monitor.domain.etcServer.model.MInsertEtcSVDiskUsage;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVMain;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVProcChk;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface etcSVMapper {


    void insertEtcSVMainData(MInsertEtcSVMain mInsertEtcSVMain);
    void insertEtcSVProcData(MInsertEtcSVProcChk mInsertEtcSVProcChk);


    public int insertEtcSVDiskData(MInsertEtcSVDiskUsage mInsertEtcSVDiskUsage) throws Exception;
    public int insertEtcSVProcessData(MInsertEtcSVProcChk mInsertEtcSVProcChk) throws Exception;
}
