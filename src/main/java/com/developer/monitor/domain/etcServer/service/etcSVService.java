package com.developer.monitor.domain.etcServer.service;

import com.developer.monitor.domain.etcServer.model.MInsertEtcSVDiskUsage;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVMain;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVProcChk;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Service;

@Service
public interface etcSVService {


    JsonNode toJsonFromEtcSVXmlData() throws Exception, JAXBException;
    String InsertEtcSVMainData(MInsertEtcSVMain mInsertEtcSVMain) throws Exception;
    String InsertEtcSVProcData(MInsertEtcSVProcChk mInsertEtcSVProcChk) throws Exception;
    void getDiskDatafromEtcSVJsonData() throws Exception;
    void getProcDatafromEtcSVJsonData() throws Exception;
}
