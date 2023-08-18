package com.developer.monitor.domain.gwServer.mapper;

import com.developer.monitor.domain.gwServer.model.MInsertGwSVClustChk;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVDiskUsage;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVMain;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVProcChk;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface gwSVMapper {

    void insertGwSVMainData(MInsertGwSVMain mInsertGwSVMain);
    void insertGwSVProcData(MInsertGwSVProcChk mInsertGwSVProcChk);
    void insertGwSVDiskData(MInsertGwSVDiskUsage mInsertGwSVDiskUsage);
    void insertGwSVClustData(MInsertGwSVClustChk mInsertGwSVClustChk);
}
