package com.developer.monitor.domain.gwServer.service;

import com.developer.monitor.domain.gwServer.model.MInsertGwSVClustChk;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVDiskUsage;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVMain;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVProcChk;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

@Service
public interface gwSVService {

    JsonNode toJsonFromGwSVXmlData(String fileName) throws Exception, JAXBException;
    void InsertGwSVMainData(MInsertGwSVMain mInsertGwSVMain) throws Exception;
    void InsertGwSVProcData(MInsertGwSVProcChk mInsertGwSVProcChk) throws Exception;
    void InsertGwSVDiskData(MInsertGwSVDiskUsage mInsertGwSVDiskUsage) throws Exception;
    void InsertGwSVClustData(MInsertGwSVClustChk mInsertGwSVClustChk) throws Exception;
}
