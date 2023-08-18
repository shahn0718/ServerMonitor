package com.developer.monitor.domain.erpServer.repository.impl;

import com.developer.monitor.common.model.XmlRootServer;
import com.developer.monitor.domain.erpServer.mapper.erpSVMapper;
import com.developer.monitor.domain.erpServer.model.MInsertErpSVDiskUsage;
import com.developer.monitor.domain.erpServer.model.MInsertErpSVMain;
import com.developer.monitor.domain.erpServer.model.MInsertErpSVProcChk;
import com.developer.monitor.domain.erpServer.repository.erpSVRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Slf4j
@Repository
public class erpSVRepositoryImpl implements erpSVRepository {


    @Autowired
    private erpSVMapper erpMapper;
    private int erpSVPkId;
    private JsonNode jsonNode;
    private ObjectMapper oM = new ObjectMapper();
    @Override
    public JsonNode toJsonFromErpSVXmlData(String fileName) throws Exception, JAXBException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        jsonNode = null;

        //JSON {"etcXmlServer":[{"hostname": ...
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlRootServer.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Object xmlErpServerData = unmarshaller.unmarshal(fileInputStream);

        //JSON {"etcXmlServer":[{"hostname": ...
        String makeJsonData = oM.writeValueAsString(xmlErpServerData);
        log.info("makeJsonData = {} ",makeJsonData);
        JsonNode erpXmlServerMainData = oM.readValue(makeJsonData, JsonNode.class);

        //JSON [{"hostname":"monitor"
        JsonNode jsonMainErpData = erpXmlServerMainData.findValue("xmlServerData");
        jsonNode = jsonMainErpData;
        log.info("jsonMainErpData = {}",jsonMainErpData);

        MInsertErpSVMain mInsertErpSVMain = new MInsertErpSVMain();
        MInsertErpSVProcChk mInsertErpSVProcChk = new MInsertErpSVProcChk();
        MInsertErpSVDiskUsage mInsertErpSVDiskUsage = new MInsertErpSVDiskUsage();

        InsertErpSVMainData(mInsertErpSVMain);
        InsertErpSVProcData(mInsertErpSVProcChk);
        InsertErpSVDiskData(mInsertErpSVDiskUsage);

        return jsonMainErpData;
    }

    @Override
    public String InsertErpSVMainData(MInsertErpSVMain mInsertErpSVMain) throws Exception {

        JsonNode erpSVInsertMainData = jsonNode;

        mInsertErpSVMain.setErpSVId(mInsertErpSVMain.getErpSVId());
        mInsertErpSVMain.setErpSVCd(String.valueOf(erpSVInsertMainData.findValue("hostname").asText()));
        mInsertErpSVMain.setErpSVOs(String.valueOf(erpSVInsertMainData.findValue("osVersion").asText()));
        mInsertErpSVMain.setErpSVIp(String.valueOf(erpSVInsertMainData.findValue("ipAddress").asText()));
        mInsertErpSVMain.setErpSVCpuUsage(erpSVInsertMainData.findValue("cpuUsage").asText());
        mInsertErpSVMain.setErpSVMemUsage(erpSVInsertMainData.findValue("memUsage").asText());
        mInsertErpSVMain.setErpSVDateTime(String.valueOf(erpSVInsertMainData.findValue("datetime").asText()) + String.valueOf(erpSVInsertMainData.findValue("timeDate").asText()));

        erpMapper.insertErpSVMainData(mInsertErpSVMain);
        erpSVPkId = mInsertErpSVMain.getErpSVId();

        return "InsertErpSVMainData";
    }
    @Override
    public String InsertErpSVProcData(MInsertErpSVProcChk mInsertErpSVProcChk) throws Exception {


        JsonNode erpSVInsertProcData = jsonNode;
        JsonNode processChk = erpSVInsertProcData.findValue("processChk");

        List<String> procJsonToList = new ArrayList<>();
        for(JsonNode jsonNode:  processChk){
            procJsonToList.add(jsonNode.asText());
        }

        HashMap<String, String> procMap = new HashMap<>();
        for(String data: procJsonToList){
            String [] array = data.split(",");
            procMap.put(array[0],array[1]);
        }

        List<MInsertErpSVProcChk> insertDbProcList = new ArrayList<>();
        Set<String> keySet = procMap.keySet();
        for(String key: keySet){
            MInsertErpSVProcChk mInsertErpSVProcChkData = new MInsertErpSVProcChk();
            mInsertErpSVProcChkData.setErpSVId(erpSVPkId);
            mInsertErpSVProcChkData.setErpSVProcCd(key);
            mInsertErpSVProcChkData.setErpSVProcChk(procMap.get(key));
            insertDbProcList.add(mInsertErpSVProcChkData);
        }

        //Insert하기
        for(MInsertErpSVProcChk mInsertErpSVProcChkData : insertDbProcList){
            erpMapper.insertErpSVProcData(mInsertErpSVProcChkData);
        }

        return "InsertErpSVProcData";
    }
    @Override
    public String InsertErpSVDiskData(MInsertErpSVDiskUsage mInsertErpSVDiskUsage) throws Exception {

        JsonNode erpSVInsertDiskData = jsonNode;

        JsonNode diskUsage = erpSVInsertDiskData.findValue("diskUsage");

        List<String> diskJsonToList = new ArrayList<>();
        for(JsonNode jsonNode: diskUsage){
            diskJsonToList.add(jsonNode.asText());
        }
        HashMap<String,String> diskMap = new HashMap<>();
        for(String data: diskJsonToList){
            String[] array = data.split(",");
            diskMap.put(array[0],array[1]);
        }

        List<MInsertErpSVDiskUsage> insertDbDiskList = new ArrayList<>();
        Set<String> keySet = diskMap.keySet();
        for(String key: keySet){
            MInsertErpSVDiskUsage mInsertErpSVDiskUsageData = new MInsertErpSVDiskUsage();
            mInsertErpSVDiskUsageData.setErpSVId(erpSVPkId);
            mInsertErpSVDiskUsageData.setErpSVDiskCd(key);
            mInsertErpSVDiskUsageData.setErpSVDiskUsage(diskMap.get(key));
            insertDbDiskList.add(mInsertErpSVDiskUsageData);
        }

        //Insert
        for(MInsertErpSVDiskUsage mInsertErpSVDiskUsageData: insertDbDiskList){
            erpMapper.insertEprSVDiskData(mInsertErpSVDiskUsageData);
        }

        return "InsertErpSVDiskData";
    }
}
