package com.developer.monitor.domain.erpServer.mapper;

import com.developer.monitor.domain.erpServer.model.MInsertErpSVDiskUsage;
import com.developer.monitor.domain.erpServer.model.MInsertErpSVMain;
import com.developer.monitor.domain.erpServer.model.MInsertErpSVProcChk;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface erpSVMapper {

    void insertErpSVMainData(MInsertErpSVMain mInsertErpSVMain);
    void insertErpSVProcData(MInsertErpSVProcChk mInsertErpSVProcChk);
    void insertEprSVDiskData(MInsertErpSVDiskUsage mInsertErpSVDiskUsage);



}
