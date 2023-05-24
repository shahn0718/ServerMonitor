package com.developer.monitor.domain.gwServer.service.impl;

import com.developer.monitor.common.model.XmlRootServer;
import com.developer.monitor.common.service.CommonService;
import com.developer.monitor.domain.gwServer.mapper.gwSVMapper;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVClustChk;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVDiskUsage;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVMain;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVProcChk;
import com.developer.monitor.domain.gwServer.service.gwSVService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


@Slf4j
@Service
public class gwSVServiceImpl implements gwSVService {

    @Autowired
    private gwSVMapper gwMapper;
    @Autowired
    private CommonService commService;
    private int gwSVPkId;
    private JsonNode jsonNode;
    private ObjectMapper oM = new ObjectMapper();
    @Override
    public JsonNode toJsonFromGwSVXmlData(String fileName) throws Exception, JAXBException {

        FileInputStream fileInputStream = new FileInputStream(fileName);
        jsonNode = null;

        JAXBContext jaxbContext = JAXBContext.newInstance(XmlRootServer.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Object xmlGwServerData = unmarshaller.unmarshal(fileInputStream);
        fileInputStream.close();

        //JSON {"etcXmlServer":[{"hostname": ...
        String makeJsonData = oM.writeValueAsString(xmlGwServerData);
        log.info("makeJsonData = {} ",makeJsonData);
        JsonNode gwXmlServerMainData = oM.readValue(makeJsonData, JsonNode.class);

        //JSON [{"hostname":"monitor"
        JsonNode jsonMainGwData = gwXmlServerMainData.findValue("etcXmlServer");
        jsonNode = jsonMainGwData;
        log.info("jsonMainGwData = {}",jsonMainGwData);

        MInsertGwSVMain mInsertGwSVMain = new MInsertGwSVMain();
        MInsertGwSVProcChk mInsertGwSVProcChk =new MInsertGwSVProcChk();
        MInsertGwSVDiskUsage mInsertGwSVDiskUsage = new MInsertGwSVDiskUsage();
        MInsertGwSVClustChk mInsertGwSVClustChk = new MInsertGwSVClustChk();

//        InsertGwSVMainData(mInsertGwSVMain);
//        InsertGwSVProcData(mInsertGwSVProcChk);
//        InsertGwSVDiskData(mInsertGwSVDiskUsage);
//        InsertGwSVClustData(mInsertGwSVClustChk);

        return null;
    }

    @Override
    public String InsertGwSVMainData(MInsertGwSVMain mInsertGwSVMain) throws Exception {

        //JsonNode gwSVInsertMainData = jsonNode;
        //




        return null;
    }
    @Override
    public String InsertGwSVProcData(MInsertGwSVProcChk mInsertGwSVProcChk) throws Exception {

        JsonNode gwSVInsertProcData = jsonNode;
        JsonNode processChk = gwSVInsertProcData.findValue("processChk");

        List<String> procJsonToList = new ArrayList<>();
        for(JsonNode jsonNode: processChk){
            procJsonToList.add(jsonNode.asText());
        }

        HashMap<String,String> procMap = new HashMap<>();
        for(String data : procJsonToList){
            String[] array = data.split(",");
            procMap.put(array[0],array[1]);
        }

        List<MInsertGwSVProcChk> insertDbProcList = new ArrayList<>();

        Set<String> keySet = procMap.keySet();
        for(String key: keySet) {
            /**
             *
             */
        }
        for(MInsertGwSVProcChk mInsertGwSVProcChkData : insertDbProcList){
            /**
             *
             */
        }


        return "OK";
    }

    @Override
    public String InsertGwSVDiskData(MInsertGwSVDiskUsage mInsertGwSVDiskUsage) throws Exception {
        return null;
    }

    @Override
    public String InsertGwSVClustData(MInsertGwSVClustChk mInsertGwSVClustChk) throws Exception {
        return null;
    }
}
