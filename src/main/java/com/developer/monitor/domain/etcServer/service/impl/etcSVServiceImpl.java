package com.developer.monitor.domain.etcServer.service.impl;


import com.developer.monitor.domain.etcServer.mapper.etcSVMapper;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVDiskUsage;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVMain;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVProcChk;
import com.developer.monitor.domain.etcServer.service.etcSVService;
import com.developer.monitor.global.model.xmlRootServer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


import com.fasterxml.jackson.core.JsonParser;
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
    private etcSVMapper etcSVMapper;

    //etcSVId (PK)
    private int etcSVPkId;
    
    
    @Override
    public JsonNode toJsonFromEtcSVXmlData() throws Exception, JAXBException {

        FileInputStream fileInputStream = new FileInputStream("xmlTestFile/serverInfo.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(xmlRootServer.class);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Object xmlEtcServerData =  unmarshaller.unmarshal(fileInputStream);
        fileInputStream.close();

        ObjectMapper oM = new ObjectMapper();

        // ObjectMapper oMapper = new ObjectMapper();
        //JSON {"etcXmlServer":[{"hostname": ...
        String makeJsonData= oM.writeValueAsString(xmlEtcServerData);
        log.info("makeJsonData = {} ",makeJsonData);
        JsonNode etcXmlServerMainData = oM.readValue(makeJsonData, JsonNode.class);

        //JSON [{"hostname":"monitor"
        JsonNode jsonMainEtcData = etcXmlServerMainData.findValue("etcXmlServer");
        log.info("jsonMainEtcData = {}",jsonMainEtcData);

        //JSON ["/,5","/boot,43"]
        JsonNode jsonEtcDiskData = jsonMainEtcData.findValue("diskUsage");
        log.info("jsonEtcDiskData = {}", jsonEtcDiskData);

        // /,5
        String s = jsonEtcDiskData.path(0).asText();
        
        
        JsonNode jsonEtcProcData = jsonMainEtcData.findValue("processChk");
        log.info("jsonEtcProcData = {}", jsonEtcProcData);
        
        
        //아래 데이터로 데이터 가져오기
        String hostname = String.valueOf(jsonMainEtcData.findValue("hostname"));
        log.info("hostname ={}",hostname);

        String osVersion = String.valueOf(jsonMainEtcData.findValue("osVersion"));
        log.info("osVersion ={}",osVersion);

        return jsonMainEtcData;

    }
    @Override
    public void getDiskDatafromEtcSVJsonData() throws Exception {


//        List<String> insertDiskData = new ArrayList<>();
//        HashMap<String,String> insertDataHashMap = new HashMap<>();
//
//        List<String> getDiskUsageData = getMainDataFromEtcSVData().stream()
//                .map(MXmlGetEtcSVEntity::getDiskUsage)
//                .flatMap(List::stream)
//                .collect(Collectors.toList());
//
//        //예시 데이터 [/,5  , /boot,43]
//        System.out.println("getDiskUsageData = " + getDiskUsageData);
//
//        //Step1 [ /boot, 43, /, 5 ]
//        for (String getDiskUsageDatas : getDiskUsageData) {
//            for (String s : getDiskUsageDatas.split(",")) {
//                insertDiskData.add(s);
//            }
//        }

//        for(int i=0; i<insertDiskData.size(); i+=2){
//            insertDataHashMap.put(insertDiskData.get(i),insertDiskData.get(i+1));
//        }
//
//        System.out.println("insertDataHashMap = " + insertDataHashMap);
////
//        return getDiskUsageData;

    }
    @Override
    public void getProcDatafromEtcSVJsonData() throws Exception {
//        List<String> getProcessChkData = getMainDataFromEtcSVData().stream()
//                .map(MXmlGetEtcSVEntity::getProcessChk)
//                .flatMap(List::stream)
//                .filter(diskData->diskData.contains(",")).collect(Collectors.toList());
//        return getProcessChkData;


    }

    @Override
    public String InsertEtcSVMainData(MInsertEtcSVMain mInsertEtcSVMain) throws Exception {

        JsonNode etcSVInsertMainData = toJsonFromEtcSVXmlData();
        log.info("etcSVInsertMainData = {}" , etcSVInsertMainData);

        mInsertEtcSVMain.setEtcSVId(mInsertEtcSVMain.getEtcSVId());
        mInsertEtcSVMain.setEtcSVCd(String.valueOf(etcSVInsertMainData.findValue("hostname").asText()));
        mInsertEtcSVMain.setEtcSVOs(String.valueOf(etcSVInsertMainData.findValue("osVersion").asText()));
        mInsertEtcSVMain.setEtcSVIp(String.valueOf(etcSVInsertMainData.findValue("ipAddress").asText()));
        mInsertEtcSVMain.setEtcSVCpuUsage(etcSVInsertMainData.findValue("cpuUsage").asText());
        mInsertEtcSVMain.setEtcSVMemUsage(etcSVInsertMainData.findValue("memUsage").asText());
        mInsertEtcSVMain.setEtcSVSwapUsage(etcSVInsertMainData.findValue("swapUsage").asText());
        mInsertEtcSVMain.setEtcSVDateTime(String.valueOf(etcSVInsertMainData.findValue("datetime").asText()) + String.valueOf(etcSVInsertMainData.findValue("timeDate").asText()));



        etcSVMapper.insertEtcSVMainData(mInsertEtcSVMain);
        etcSVPkId = mInsertEtcSVMain.getEtcSVId();

        return "OK";
    }

    @Override
    public String InsertEtcSVProcData(MInsertEtcSVProcChk mInsertEtcSVProcChk) throws Exception {

        JsonNode etcSVInsertDiskData = toJsonFromEtcSVXmlData();
        log.info("etcSVInsertDiskData = {}" , etcSVInsertDiskData);

        JsonNode processChk = etcSVInsertDiskData.findValue("processChk");
       
        List<String> procJsonTolist = new ArrayList<>();
        for (JsonNode jsonNode : processChk) {
            procJsonTolist.add(jsonNode.asText());
        }
        
        HashMap<String,String> procMap = new HashMap<>();
        for (String data : procJsonTolist) {
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
            etcSVMapper.insertEtcSVProcData(mInsertEtcSVProcChkData);
        }
        return "OK";
    }
}
