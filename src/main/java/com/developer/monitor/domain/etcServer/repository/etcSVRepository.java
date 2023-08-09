package com.developer.monitor.domain.etcServer.repository;

import com.developer.monitor.domain.etcServer.model.MInsertEtcSVDiskUsage;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVMain;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVProcChk;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.xml.bind.JAXBException;

public interface etcSVRepository {

    JsonNode toJsonFromEtcSVXmlData(String fileName) throws Exception, JAXBException;
    String InsertEtcSVMainData(MInsertEtcSVMain mInsertEtcSVMain) throws Exception;
    String InsertEtcSVProcData(MInsertEtcSVProcChk mInsertEtcSVProcChk) throws Exception;
    String InsertEtcSVDiskData(MInsertEtcSVDiskUsage mInsertEtcSVDiskUsage) throws Exception;
}
