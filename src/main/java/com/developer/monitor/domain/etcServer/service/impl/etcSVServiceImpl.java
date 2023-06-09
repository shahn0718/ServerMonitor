package com.developer.monitor.domain.etcServer.service.impl;


import com.developer.monitor.domain.etcServer.mapper.etcSVMapper;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVDiskUsage;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVMain;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVProcChk;
import com.developer.monitor.domain.etcServer.service.etcSVService;
import com.developer.monitor.common.model.XmlRootServer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.*;

import static java.util.Arrays.stream;

@Slf4j
@Service
public class etcSVServiceImpl implements etcSVService {

    @Autowired
    private etcSVMapper etcMapper;
    private int etcSVPkId;
    private JsonNode jsonNode;
    private ObjectMapper oM = new ObjectMapper();

    @Override
    public JsonNode toJsonFromEtcSVXmlData(String fileName) throws Exception, JAXBException {

        FileInputStream fileInputStream = new FileInputStream(fileName);
        jsonNode = null;

        //JSON {"etcXmlServer":[{"hostname": ...
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlRootServer.class);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Object xmlEtcServerData =  unmarshaller.unmarshal(fileInputStream);
        fileInputStream.close();

        //JSON {"etcXmlServer":[{"hostname": ...
        String makeJsonData= oM.writeValueAsString(xmlEtcServerData);
        log.info("makeJsonData = {} ",makeJsonData);
        JsonNode etcXmlServerMainData = oM.readValue(makeJsonData, JsonNode.class);

        //JSON [{"hostname":"monitor"
        JsonNode jsonMainEtcData = etcXmlServerMainData.findValue("etcXmlServer");
        jsonNode = jsonMainEtcData;
        log.info("jsonMainEtcData = {}",jsonMainEtcData);

//        //JSON ["/,5","/boot,43"]
//        JsonNode jsonEtcDiskData = jsonMainEtcData.findValue("diskUsage");
//        log.info("jsonEtcDiskData = {}", jsonEtcDiskData);
//
//        // /,5
//        String s = jsonEtcDiskData.path(0).asText();
//
//        JsonNode jsonEtcProcData = jsonMainEtcData.findValue("processChk");
//        log.info("jsonEtcProcData = {}", jsonEtcProcData);

//        MInsertEtcSVMain mInsertEtcSVMain = new MInsertEtcSVMain();
//        MInsertEtcSVProcChk mInsertEtcSVProcChk =new MInsertEtcSVProcChk();
//        MInsertEtcSVDiskUsage mInsertEtcSVDiskUsage = new MInsertEtcSVDiskUsage();
//
//        InsertEtcSVMainData(mInsertEtcSVMain);
//        InsertEtcSVProcData(mInsertEtcSVProcChk);
//        InsertEtcSVDiskData(mInsertEtcSVDiskUsage);

        return jsonMainEtcData;
    }

    @Override
    public String InsertEtcSVMainData(MInsertEtcSVMain mInsertEtcSVMain) throws Exception {

        JsonNode etcSVInsertMainData = jsonNode;

        mInsertEtcSVMain.setEtcSVId(mInsertEtcSVMain.getEtcSVId());
        mInsertEtcSVMain.setEtcSVCd(String.valueOf(etcSVInsertMainData.findValue("hostname").asText()));
        mInsertEtcSVMain.setEtcSVOs(String.valueOf(etcSVInsertMainData.findValue("osVersion").asText()));
        mInsertEtcSVMain.setEtcSVIp(String.valueOf(etcSVInsertMainData.findValue("ipAddress").asText()));
        mInsertEtcSVMain.setEtcSVCpuUsage(etcSVInsertMainData.findValue("cpuUsage").asText());
        mInsertEtcSVMain.setEtcSVMemUsage(etcSVInsertMainData.findValue("memUsage").asText());
        mInsertEtcSVMain.setEtcSVSwapUsage(etcSVInsertMainData.findValue("swapUsage").asText());
        mInsertEtcSVMain.setEtcSVDateTime(String.valueOf(etcSVInsertMainData.findValue("datetime").asText()) + String.valueOf(etcSVInsertMainData.findValue("timeDate").asText()));

        etcMapper.insertEtcSVMainData(mInsertEtcSVMain);
        etcSVPkId = mInsertEtcSVMain.getEtcSVId();

        return "OK";
    }

    @Override
    public String InsertEtcSVProcData(MInsertEtcSVProcChk mInsertEtcSVProcChk) throws Exception {

        JsonNode etcSVInsertProcData = jsonNode;
        JsonNode processChk = etcSVInsertProcData.findValue("processChk");
       
        List<String> procJsonToList = new ArrayList<>();
        for (JsonNode jsonNode : processChk) {
            procJsonToList.add(jsonNode.asText());
        }
        
        HashMap<String,String> procMap = new HashMap<>();
        for (String data : procJsonToList) {
            String[] array = data.split(",");
            procMap.put(array[0],array[1]);
        }
        
        List<MInsertEtcSVProcChk> insertDbProcList = new ArrayList<>();
        
        Set<String> keySet = procMap.keySet();
        for(String key: keySet){
            MInsertEtcSVProcChk mInsertEtcSVProcChkData = new MInsertEtcSVProcChk();
            mInsertEtcSVProcChkData.setEtcSVId(etcSVPkId);
            mInsertEtcSVProcChkData.setEtcSVProcCd(key);
            mInsertEtcSVProcChkData.setEtcSVProcChk(procMap.get(key));
            insertDbProcList.add(mInsertEtcSVProcChkData);
        }
        //Insert 하기
        for(MInsertEtcSVProcChk mInsertEtcSVProcChkData: insertDbProcList){
            etcMapper.insertEtcSVProcData(mInsertEtcSVProcChkData);
        }
        return "OK";
    }

    @Override
    public String InsertEtcSVDiskData(MInsertEtcSVDiskUsage mInsertEtcSVDiskUsage) throws Exception {

        JsonNode etcSVInsertDiskData = jsonNode;

        JsonNode diskUsage = etcSVInsertDiskData.findValue("diskUsage");

        List<String> diskJsonToList = new ArrayList<>();
        for(JsonNode jsonNode: diskUsage){
            diskJsonToList.add(jsonNode.asText());
        }
        HashMap<String,String> diskMap = new HashMap<>();
        for(String data: diskJsonToList){
            String[] array = data.split(",");
            diskMap.put(array[0],array[1]);
        }

        List<MInsertEtcSVDiskUsage> insertDbDiskList = new ArrayList<>();
        Set<String> keySet = diskMap.keySet();
        for(String key: keySet){
            MInsertEtcSVDiskUsage mInsertEtcSVDiskUsageData = new MInsertEtcSVDiskUsage();
            mInsertEtcSVDiskUsageData.setEtcSVId(etcSVPkId);
            mInsertEtcSVDiskUsageData.setEtcSVDiskCd(key);
            mInsertEtcSVDiskUsageData.setEtcSVDiskUsage(diskMap.get(key));
            insertDbDiskList.add(mInsertEtcSVDiskUsageData);
        }
        //Insert
        for(MInsertEtcSVDiskUsage mInsertEtcSVDiskUsageData: insertDbDiskList){
            etcMapper.insertEtcSVDiskData(mInsertEtcSVDiskUsageData);
        }
        return " ";
    }
//    @Override
//    public void SendFileToJson() throws Exception{
//
//        String filePath = "C:\\\\upIt\\\\monitor\\\\monitor\\\\xmlTestFile";
//        List<File> fileList = common.getFileFromDir(filePath);;
//        for(File fileName : fileList){
//            toJsonFromEtcSVXmlData(String.valueOf(fileName));
//        }
//    }
}
