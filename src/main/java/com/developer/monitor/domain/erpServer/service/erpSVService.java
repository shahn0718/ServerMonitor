package com.developer.monitor.domain.erpServer.service;

import com.developer.monitor.domain.erpServer.model.MInsertErpSVDiskUsage;
import com.developer.monitor.domain.erpServer.model.MInsertErpSVMain;
import com.developer.monitor.domain.erpServer.model.MInsertErpSVProcChk;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Service;

@Service
public interface erpSVService {

    JsonNode toJsonFromErpSVXmlData(String fileName) throws Exception, JAXBException;
    void InsertErpSVMainData(MInsertErpSVMain mInsertErpSVMain) throws Exception;
    void InsertErpSVProcData(MInsertErpSVProcChk mInsertErpSVProcChk) throws Exception;
    void InsertErpSVDiskData(MInsertErpSVDiskUsage mInsertErpSVDiskUsage) throws Exception;
}
