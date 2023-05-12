package com.developer.monitor.domain.etcServer.service.impl;


import aj.org.objectweb.asm.TypeReference;
import com.developer.monitor.domain.etcServer.mapper.etcSVMapper;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVDiskUsage;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVMain;
import com.developer.monitor.domain.etcServer.model.MXmlGetEtcSVEntity;
import com.developer.monitor.domain.etcServer.service.etcSVService;
import com.developer.monitor.global.model.xmlRootServer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.DataInput;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Slf4j
@Service
public class etcSVServiceImpl implements etcSVService {

    @Autowired
    private etcSVMapper etcSVMapper;

    @Override
    public List<MXmlGetEtcSVEntity> getMainDataFromEtcSVData() throws Exception, JAXBException {

        FileInputStream fileInputStream = new FileInputStream("xmlTestFile/serverInfo.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(xmlRootServer.class);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        Object xmlRootServerData =  unmarshaller.unmarshal(fileInputStream);
        fileInputStream.close();

        ObjectMapper oMapper = new ObjectMapper();
        //List<MXmlGetEtcSVEntity> etcSVData = stream(xmlRootServerData.getEtcXmlServer()).collect(Collectors.toList());
//        for(int i=0; i<etcSVData.size(); i++){
//            System.out.println("mainDataFromEtcSVData.get(i) = " + etcSVData.stream().map(MXmlGetEtcSVEntity::toString));
//        }
        String testString = oMapper.writeValueAsString(xmlRootServerData);
        System.out.println("KK = " + testString);
        JsonNode map = oMapper.readValue(testString, JsonNode.class);
        //MXmlGetEtcSVEntity mXmlGetEtcSVEntity = oMapper.readValue(testString, MXmlGetEtcSVEntity.class);

        JsonNode testJSON = map;
        JsonNode etcXmlServer2 = map.findValue("etcXmlServer");
        System.out.println("etcXmlServer2 = " + etcXmlServer2);

        //아래 데이터로 데이터 가져오기
        String hostname = String.valueOf(etcXmlServer2.findValue("hostname"));
        System.out.println("hostname = " + hostname);

        List<MXmlGetEtcSVEntity>kkt = new ArrayList<>();

        return kkt;

    }
    @Override
    public List<String> getDiskDatafromEtcSVData() throws Exception {

        List<String> insertDiskData = new ArrayList<>();
        HashMap<String,String> insertDataHashMap = new HashMap<>();

        List<String> getDiskUsageData = getMainDataFromEtcSVData().stream()
                .map(MXmlGetEtcSVEntity::getDiskUsage)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        //예시 데이터 [/,5  , /boot,43]
        System.out.println("getDiskUsageData = " + getDiskUsageData);

        //Step1 [ /boot, 43, /, 5 ]
        for (String getDiskUsageDatas : getDiskUsageData) {
            for (String s : getDiskUsageDatas.split(",")) {
                insertDiskData.add(s);
            }
        }

//        for(int i=0; i<insertDiskData.size(); i+=2){
//            insertDataHashMap.put(insertDiskData.get(i),insertDiskData.get(i+1));
//        }
//
//        System.out.println("insertDataHashMap = " + insertDataHashMap);
//
        return getDiskUsageData;
    }
    @Override
    public List<String> getProcessDatafromEtcSVData() throws Exception {
        List<String> getProcessChkData = getMainDataFromEtcSVData().stream()
                .map(MXmlGetEtcSVEntity::getProcessChk)
                .flatMap(List::stream)
                .filter(diskData->diskData.contains(",")).collect(Collectors.toList());
        return getProcessChkData;
    }

    private void insertEtcSVMainData() throws Exception {


        Map<String,String> map = new HashMap<>() ;
        List<MXmlGetEtcSVEntity> mainDataFromEtcSVData = getMainDataFromEtcSVData();
        for(int i=0; i<mainDataFromEtcSVData.size(); i++){
            System.out.println("mainDataFromEtcSVData.get(i) = " + mainDataFromEtcSVData.get(i));
        }
    }
}
