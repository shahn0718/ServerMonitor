package com.developer.monitor.domain.etcServer.controller;

import com.developer.monitor.common.model.ServerFilePath;
import com.developer.monitor.common.service.CommonService;
import com.developer.monitor.domain.etcServer.mapper.etcSVMapper;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVDiskUsage;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVMain;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVProcChk;
import com.developer.monitor.domain.etcServer.model.MXmlGetEtcSVEntity;
import com.developer.monitor.domain.etcServer.service.etcSVService;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
@Slf4j
public class etcSVController {

    @Autowired
    private etcSVService etcService;
    @Autowired
    private CommonService cmnService;

    @PostMapping(value="/getEtcSVDataList")
    @Scheduled(cron = "0 */5 * * * *")
    public void getEtcSVDataList() throws Exception {

        ServerFilePath filePath = new ServerFilePath();
        List<File> fileListFromDir = cmnService.getFileFromDir(filePath.etcSVFilePath);
        for(File fileName: fileListFromDir){
            etcService.toJsonFromEtcSVXmlData(String.valueOf(fileName));
        }
    }

    @RequestMapping(value="/insertProcData", method={RequestMethod.POST})
    public String InsertTest(MInsertEtcSVMain mInsertEtcSVMain) throws Exception {




        etcService.InsertEtcSVMainData(mInsertEtcSVMain);
        return "OK";
    }

    @RequestMapping(value="/insertDisk", method={RequestMethod.POST})
    public String InsertTest(MInsertEtcSVProcChk mInsertEtcSVProcChk) throws Exception {

        etcService.InsertEtcSVProcData(mInsertEtcSVProcChk);
        return "OK";
    }

    @RequestMapping(value="/getFileList", method={RequestMethod.POST})
    public String getFileList() throws Exception {

        //etcService.findFileList();
        return "OK";
    }


//    public List<String> getDiskUsageDataList() throws Exception {
//        return etcService.getDiskDatafromEtcSVData();
//    }
//
//    public List<String> getProcessChkDataList() throws Exception{
//        return etcService.getProcessDatafromEtcSVData();
//    }
}
