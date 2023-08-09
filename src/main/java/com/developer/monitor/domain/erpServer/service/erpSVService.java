package com.developer.monitor.domain.erpServer.service;

import com.developer.monitor.domain.erpServer.model.MInsertErpSVDiskUsage;
import com.developer.monitor.domain.erpServer.model.MInsertErpSVMain;
import com.developer.monitor.domain.erpServer.model.MInsertErpSVProcChk;
import com.developer.monitor.domain.erpServer.repository.erpSVRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class erpSVService {

    private final erpSVRepository erpRepository;

    @Autowired
    public erpSVService(erpSVRepository erpRepository) {
        this.erpRepository =erpRepository;
    }

    public JsonNode toJsonFromErpSVXmlData(String fileName) throws Exception{
        return erpRepository.toJsonFromErpSVXmlData(fileName);
    }
    public String InsertErpSVMainData(MInsertErpSVMain insertErpSVMain) throws Exception {
        return erpRepository.InsertErpSVMainData(insertErpSVMain);
    }
    public String InsertErpSVProcData(MInsertErpSVProcChk insertErpSVProcChk) throws Exception{
        return erpRepository.InsertErpSVProcData(insertErpSVProcChk);
    }
    public String InsertErpSVDiskData(MInsertErpSVDiskUsage InsertErpSVDiskUsage) throws Exception{
        return erpRepository.InsertErpSVDiskData(InsertErpSVDiskUsage);
    }
}
