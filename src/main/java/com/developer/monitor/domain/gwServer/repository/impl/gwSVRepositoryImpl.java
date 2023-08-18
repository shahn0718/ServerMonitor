package com.developer.monitor.domain.gwServer.repository.impl;

import com.developer.monitor.common.model.XmlRootServer;
import com.developer.monitor.domain.gwServer.mapper.gwSVMapper;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVClustChk;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVDiskUsage;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVMain;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVProcChk;
import com.developer.monitor.domain.gwServer.repository.gwSVRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Slf4j
@Repository
public class gwSVRepositoryImpl implements gwSVRepository {


    @Autowired
    private gwSVMapper gwMapper;
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
        log.info("makeJsonData = {} ", makeJsonData);
        JsonNode gwXmlServerMainData = oM.readValue(makeJsonData, JsonNode.class);

        //JSON [{"hostname":"monitor"
        JsonNode jsonMainGwData = gwXmlServerMainData.findValue("xmlServerData");
        jsonNode = jsonMainGwData;
        log.info("jsonMainGwData = {}", jsonMainGwData);

        MInsertGwSVMain mInsertGwSVMain = new MInsertGwSVMain();
        MInsertGwSVProcChk mInsertGwSVProcChk = new MInsertGwSVProcChk();
        MInsertGwSVDiskUsage mInsertGwSVDiskUsage = new MInsertGwSVDiskUsage();
        MInsertGwSVClustChk mInsertGwSVClustChk = new MInsertGwSVClustChk();

        InsertGwSVMainData(mInsertGwSVMain);
        InsertGwSVProcData(mInsertGwSVProcChk);
        InsertGwSVDiskData(mInsertGwSVDiskUsage);
        InsertGwSVClustData(mInsertGwSVClustChk);

        return jsonMainGwData;
    }

    @Override
    public String InsertGwSVMainData(MInsertGwSVMain mInsertGwSVMain) throws Exception {

        JsonNode gwSVInsertMainData = jsonNode;

        mInsertGwSVMain.setGwSVId(mInsertGwSVMain.getGwSVId());
        mInsertGwSVMain.setGwSVCd(String.valueOf(gwSVInsertMainData.findValue("hostname").asText()));
        mInsertGwSVMain.setGwSVOs(String.valueOf(gwSVInsertMainData.findValue("osVersion").asText()));
        mInsertGwSVMain.setGwSVIp(String.valueOf(gwSVInsertMainData.findValue("ipAddress").asText()));
        mInsertGwSVMain.setGwSVCpuUsage(gwSVInsertMainData.findValue("cpuUsage").asText());
        mInsertGwSVMain.setGwSVMemUsage(gwSVInsertMainData.findValue("memUsage").asText());
        //mInsertGwSVMain.setGwSVSwapUsage(gwSVInsertMainData.findValue("swapUsage").asText());
        mInsertGwSVMain.setGwSVDateTime(String.valueOf(gwSVInsertMainData.findValue("datetime").asText()) + String.valueOf(gwSVInsertMainData.findValue("timeDate").asText()));

        gwMapper.insertGwSVMainData(mInsertGwSVMain);
        gwSVPkId = mInsertGwSVMain.getGwSVId();

        return "InsertGwSVMainData";
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
            MInsertGwSVProcChk mInsertGwSVProcChkData = new MInsertGwSVProcChk();
            mInsertGwSVProcChkData.setGwSVId(gwSVPkId);
            mInsertGwSVProcChkData.setGwSVProcCd(key);
            mInsertGwSVProcChkData.setGwSVProcChk(procMap.get(key));
            insertDbProcList.add(mInsertGwSVProcChkData);
        }
        /**
         * procMap = {java=25, mysql=15}
         * keySet = [java, mysql]
         *
         */
        log.info("procMap = {} ",procMap);

        for(MInsertGwSVProcChk mInsertGwSVProcChkData : insertDbProcList){
            gwMapper.insertGwSVProcData(mInsertGwSVProcChkData);
        }
        return "InsertGwSVProcData";
    }

    @Override
    public String InsertGwSVDiskData(MInsertGwSVDiskUsage mInsertGwSVDiskUsage) throws Exception {
        JsonNode gwSVInsertDiskData = jsonNode;
        JsonNode diskUsage = gwSVInsertDiskData.findValue("diskUsage");

        List<String> diskJsonToList = new ArrayList<>();
        for(JsonNode jsonNode: diskUsage){
            diskJsonToList.add(jsonNode.asText());
        }

        HashMap<String,String> diskMap = new HashMap<>();
        for(String data: diskJsonToList){
            String[] array = data.split(",");
            diskMap.put(array[0],array[1]);
        }

        log.info("diskMap = {} ",diskMap);

        List<MInsertGwSVDiskUsage> insertDbDiskList = new ArrayList<>();
        Set<String> keySet = diskMap.keySet();
        for(String key: keySet){
            MInsertGwSVDiskUsage mInsertGwSVDiskUsageData = new MInsertGwSVDiskUsage();
            mInsertGwSVDiskUsageData.setGwSVId(gwSVPkId);
            mInsertGwSVDiskUsageData.setGwSVDiskCd(key);
            mInsertGwSVDiskUsageData.setGwSVDiskUsage(diskMap.get(key));
            insertDbDiskList.add(mInsertGwSVDiskUsageData);
        }

        //Insert
        for(MInsertGwSVDiskUsage mInsertGwSVDiskUsageData : insertDbDiskList){
            gwMapper.insertGwSVDiskData(mInsertGwSVDiskUsageData);
        }

        return "InsertGwSVDiskData";
    }

    @Override
    public String InsertGwSVClustData(MInsertGwSVClustChk mInsertGwSVClustChk) throws Exception {
        /**
         * <cluster_chk>kube-system,coredns-6f57957999-bl8fh,Pending</cluster_chk>
         * status 가 정상적인상태 (Running or Completed) 에서는 항목이 생성 안됨, 다른 상태값이 있을 경우만 생성되는 케이스
         */

        JsonNode gwSVInsertClustData = jsonNode;
        JsonNode clustUsage = gwSVInsertClustData.findValue("clusterChk");

        List<String> clustJsonToList = new ArrayList<>();
        for(JsonNode jsonNode : clustUsage){
            clustJsonToList.add(jsonNode.asText());
        }

        /**
         *  clustJsonToList (ArrayList) 에서 ',' 기준으로 분리 → array
         *
         *  <cluster-chk></cluster-chk>
         *  status에 따라서 인수가 2 or 3 개 생김
         */
        HashMap<String,String> clustMap = new HashMap<>();
        List<String> clustList = new ArrayList<>();

        //향후, 수정이 필요한 부분이되지 않을까?
        for(String data: clustJsonToList){
            String[] array = data.split(",");
            if(array.length > 2){
                for(int i=0; i<array.length; i++)
                    clustList.add(array[i]);
            }else{
                clustMap.put(array[0],array[1]);
            }
        }

        log.info("clustList = {} ",clustList);
        log.info("clustMap = {} ",clustMap);

        List<MInsertGwSVClustChk> insertDbClustList = new ArrayList<>();
        int cnt=0;
        int listNum=0;
        while(cnt<clustList.size()/3) {
            MInsertGwSVClustChk mInsertGwSVClustChkData = new MInsertGwSVClustChk();
            mInsertGwSVClustChkData.setGwSVId(gwSVPkId);
            mInsertGwSVClustChkData.setGwSVClustCd(clustList.get(listNum));
            mInsertGwSVClustChkData.setGwSVClustPodName(clustList.get(listNum+1));
            mInsertGwSVClustChkData.setGwSVClustStatus(clustList.get(listNum+2));
            insertDbClustList.add(mInsertGwSVClustChkData);
            cnt+=1;
            listNum+=3;
        }
        Set<String> keySet = clustMap.keySet();
        if(!clustMap.isEmpty()){
            for(String key: keySet){
                MInsertGwSVClustChk mInsertGwSVClustChkData = new MInsertGwSVClustChk();
                mInsertGwSVClustChkData.setGwSVId(gwSVPkId);
                mInsertGwSVClustChkData.setGwSVClustCd(key);
                mInsertGwSVClustChkData.setGwSVClustPodName(clustMap.get(key));
                insertDbClustList.add(mInsertGwSVClustChkData);
            }
        }
        //Insert
        for(MInsertGwSVClustChk mInsertGwSVClustChkData : insertDbClustList){
            gwMapper.insertGwSVClustData(mInsertGwSVClustChkData);
        }

        return "InsertGwSVClustData";
    }
}
