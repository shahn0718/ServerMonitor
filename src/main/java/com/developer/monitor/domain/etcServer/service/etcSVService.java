package com.developer.monitor.domain.etcServer.service;


import com.developer.monitor.domain.etcServer.model.MInsertEtcSVDiskUsage;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVMain;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVProcChk;
import com.developer.monitor.domain.etcServer.repository.etcSVRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class etcSVService {

    private final etcSVRepository etcRepository;

    @Autowired
    public etcSVService(etcSVRepository etcRepository){
        this.etcRepository = etcRepository;
    }

    public JsonNode toJsonFromEtcSVXmlData(String fileName) throws Exception{
        return etcRepository.toJsonFromEtcSVXmlData(fileName);
    }

    public String InsertEtcSVMainData(MInsertEtcSVMain insertEtcSVMain) throws Exception{
        return etcRepository.InsertEtcSVMainData(insertEtcSVMain);
    }

    public String InsertEtcSVProcData(MInsertEtcSVProcChk insertEtcSVProcChk) throws Exception{
        return etcRepository.InsertEtcSVProcData(insertEtcSVProcChk);
    }

    public String InsertEtcSVDiskData(MInsertEtcSVDiskUsage insertEtcSVDiskUsage) throws Exception{
        return etcRepository.InsertEtcSVDiskData(insertEtcSVDiskUsage);
    }

}
