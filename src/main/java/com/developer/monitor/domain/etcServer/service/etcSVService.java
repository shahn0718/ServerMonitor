package com.developer.monitor.domain.etcServer.service;

import com.developer.monitor.domain.etcServer.model.MInsertEtcSVDiskUsage;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVMain;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVProcChk;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public interface etcSVService {

    JsonNode toJsonFromEtcSVXmlData(String fileName) throws Exception, JAXBException;
    String InsertEtcSVMainData(MInsertEtcSVMain mInsertEtcSVMain) throws Exception;
    String InsertEtcSVProcData(MInsertEtcSVProcChk mInsertEtcSVProcChk) throws Exception;
    String InsertEtcSVDiskData(MInsertEtcSVDiskUsage mInsertEtcSVDiskUsage) throws Exception;
//    public void SendFileToJson() throws Exception;
}
