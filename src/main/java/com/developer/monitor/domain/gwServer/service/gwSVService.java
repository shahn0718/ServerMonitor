package com.developer.monitor.domain.gwServer.service;


import com.developer.monitor.domain.gwServer.model.MInsertGwSVClustChk;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVDiskUsage;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVMain;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVProcChk;
import com.developer.monitor.domain.gwServer.repository.gwSVRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class gwSVService {

    private final gwSVRepository gwRepository;

    @Autowired
    public gwSVService(gwSVRepository gwRepository){
        this.gwRepository= gwRepository;
    }

    public JsonNode toJsonFromGwSVXmlData(String fileName) throws Exception{
        return gwRepository.toJsonFromGwSVXmlData(fileName);
    }

    public String InsertGwSVMainData(MInsertGwSVMain mInsertGwSVMain) throws Exception{
        return gwRepository.InsertGwSVMainData(mInsertGwSVMain);
    }

    public String InsertGwSVProcData(MInsertGwSVProcChk mInsertGwSVProcChk) throws Exception{
        return gwRepository.InsertGwSVProcData(mInsertGwSVProcChk);
    }

    public String InsertGwSVDiskData(MInsertGwSVDiskUsage mInsertGwSVDiskUsage)throws Exception {
        return gwRepository.InsertGwSVDiskData(mInsertGwSVDiskUsage);
    }

    public String InsertGwSVClustData(MInsertGwSVClustChk mInsertGwSVClustChk) throws Exception {
        return gwRepository.InsertGwSVClustData(mInsertGwSVClustChk);
    }
}
