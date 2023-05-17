package com.developer.monitor.domain.etcServer.controller;

import com.developer.monitor.domain.etcServer.mapper.etcSVMapper;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVDiskUsage;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVMain;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVProcChk;
import com.developer.monitor.domain.etcServer.model.MXmlGetEtcSVEntity;
import com.developer.monitor.domain.etcServer.service.etcSVService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class etcSVController {

    @Autowired
    private etcSVService etcService;

    @RequestMapping(value="/hello", method={RequestMethod.POST})
    @Scheduled(cron = "0 */5 * * * *")
    public void getEtcSVDataList() throws Exception {

//        MInsertEtcSVMain mInsertEtcSVMain = new MInsertEtcSVMain();
//        MInsertEtcSVProcChk mInsertEtcSVProcChk =new MInsertEtcSVProcChk();
//        MInsertEtcSVDiskUsage mInsertEtcSVDiskUsage = new MInsertEtcSVDiskUsage();
//
//        etcService.toJsonFromEtcSVXmlData();
//
          etcService.SendFileToJson();
//        etcService.InsertEtcSVMainData(mInsertEtcSVMain);
//        etcService.InsertEtcSVProcData(mInsertEtcSVProcChk);
//        etcService.InsertEtcSVDiskData(mInsertEtcSVDiskUsage);
    }

    @RequestMapping(value="/insertData", method={RequestMethod.POST})
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

        etcService.SendFileToJson();
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
