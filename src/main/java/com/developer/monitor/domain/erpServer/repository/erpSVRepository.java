package com.developer.monitor.domain.erpServer.repository;

import com.developer.monitor.domain.erpServer.model.MInsertErpSVDiskUsage;
import com.developer.monitor.domain.erpServer.model.MInsertErpSVMain;
import com.developer.monitor.domain.erpServer.model.MInsertErpSVProcChk;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.xml.bind.JAXBException;

public interface erpSVRepository {

    JsonNode toJsonFromErpSVXmlData(String fileName) throws Exception, JAXBException;
    void InsertErpSVMainData(MInsertErpSVMain mInsertErpSVMain) throws Exception;
    void InsertErpSVProcData(MInsertErpSVProcChk mInsertErpSVProcChk) throws Exception;
    void InsertErpSVDiskData(MInsertErpSVDiskUsage mInsertErpSVDiskUsage) throws Exception;
}
