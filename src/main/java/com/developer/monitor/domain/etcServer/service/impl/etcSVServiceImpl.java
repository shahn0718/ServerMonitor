package com.developer.monitor.domain.etcServer.service.impl;


import com.developer.monitor.domain.etcServer.mapper.etcSVMapper;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVDiskUsage;
import com.developer.monitor.domain.etcServer.model.MXmlGetEtcSVEntity;
import com.developer.monitor.domain.etcServer.service.etcSVService;
import com.developer.monitor.global.model.xmlRootServer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Slf4j
@Service
public class etcSVServiceImpl implements etcSVService {

    private etcSVMapper etcSVMapper;

    @Override
    public List<MXmlGetEtcSVEntity> getMainDataFromEtcSVData() throws Exception, JAXBException {

        FileInputStream fileInputStream = new FileInputStream("xmlTestFile/serverInfo.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(xmlRootServer.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        xmlRootServer xmlRootServerData = (xmlRootServer) unmarshaller.unmarshal(fileInputStream);
        fileInputStream.close();

        List<MXmlGetEtcSVEntity> etcSVData = stream(xmlRootServerData.getEtcXmlServer()).collect(Collectors.toList());

        return etcSVData;

    }
    @Override
    public List<String> getDiskDatafromEtcSVData() throws Exception {

        List<String> insertDiskData = new ArrayList<>();
        HashMap<String,String> insertData = new HashMap<>();
        List<String> getDiskUsageData = getMainDataFromEtcSVData().stream()
                .map(MXmlGetEtcSVEntity::getDiskUsage)
                .flatMap(List::stream)
                .collect(Collectors.toList());


        //예시 데이터 /,5  , /boot,43
        for (String getDiskUsageDatas : getDiskUsageData) {
            for (String s : getDiskUsageDatas.split(",")) {
                insertDiskData.add(s);
            }
        }
        for(int i=0; i<insertDiskData.size(); i+=2){
            insertData.put("",insertDiskData.get(i+1));}
        System.out.println("insertData = " + insertData);


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
}
