package com.developer.monitor.domain.etcServer.controller;

import com.developer.monitor.common.model.ServerFilePath;
import com.developer.monitor.common.service.CommonService;
import com.developer.monitor.common.service.FileService;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVDiskUsage;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVMain;
import com.developer.monitor.domain.etcServer.model.MInsertEtcSVProcChk;


import com.developer.monitor.domain.etcServer.service.etcSVService;
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
    private FileService fileService;

    @PostMapping(value = "/getEtcSVXmlList")
    //@Scheduled(cron = "0 */5 * * * *")
    @Scheduled(cron = "20 * * * * *")
    public void getEtcSVXmlList() throws Exception {
        /**
         *  toJsonFromEtcSVXmlData 여기에 insertMain / insertDisk / insertProc 포함.
         */
        ServerFilePath filePath = new ServerFilePath();
        List<File> fileListFromDir = fileService.getFileFromDir(filePath.etcSVFilePath);
        for (File fileName : fileListFromDir) {
            log.info("etcSVFileName = {}", fileName);
            etcService.toJsonFromEtcSVXmlData(String.valueOf(fileName));
        }
    }
    @RequestMapping(value = "/insertEtcMain", method = {RequestMethod.POST})
    public String insertEtcSVMainData(MInsertEtcSVMain mInsertEtcSVMain) throws Exception {
        etcService.InsertEtcSVMainData(mInsertEtcSVMain);
        return "OK";
    }
    @RequestMapping(value = "/insertEtcProc", method = {RequestMethod.POST})
    public String insertEtcSVProcData(MInsertEtcSVProcChk mInsertEtcSVProcChk) throws Exception {
        etcService.InsertEtcSVProcData(mInsertEtcSVProcChk);
        return "OK";
    }
    @RequestMapping(value="/insertEtcDisk", method = {RequestMethod.POST})
    public String insertEtcSVDiskData(MInsertEtcSVDiskUsage mInsertEtcSVDiskUsage) throws Exception {
        etcService.InsertEtcSVDiskData(mInsertEtcSVDiskUsage);
        return "OK";
    }
}
