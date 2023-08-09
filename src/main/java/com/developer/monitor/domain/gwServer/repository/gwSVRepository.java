package com.developer.monitor.domain.gwServer.repository;

import com.developer.monitor.domain.gwServer.model.MInsertGwSVClustChk;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVDiskUsage;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVMain;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVProcChk;
import com.fasterxml.jackson.databind.JsonNode;

import javax.xml.bind.JAXBException;

public interface gwSVRepository {

    JsonNode toJsonFromGwSVXmlData(String fileName) throws Exception, JAXBException;
    String InsertGwSVMainData(MInsertGwSVMain mInsertGwSVMain) throws Exception;
    String InsertGwSVProcData(MInsertGwSVProcChk mInsertGwSVProcChk) throws Exception;
    String InsertGwSVDiskData(MInsertGwSVDiskUsage mInsertGwSVDiskUsage) throws Exception;
    String InsertGwSVClustData(MInsertGwSVClustChk mInsertGwSVClustChk) throws Exception;

}
