package com.developer.monitor.domain.erpServer.repository;

import com.developer.monitor.domain.erpServer.model.MInsertErpSVDiskUsage;
import com.developer.monitor.domain.erpServer.model.MInsertErpSVMain;
import com.developer.monitor.domain.erpServer.model.MInsertErpSVProcChk;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.xml.bind.JAXBException;

public interface erpSVRepository {

    JsonNode toJsonFromErpSVXmlData(String fileName) throws Exception, JAXBException;
    String InsertErpSVMainData(MInsertErpSVMain mInsertErpSVMain) throws Exception;
    String InsertErpSVProcData(MInsertErpSVProcChk mInsertErpSVProcChk) throws Exception;
    String InsertErpSVDiskData(MInsertErpSVDiskUsage mInsertErpSVDiskUsage) throws Exception;
}
