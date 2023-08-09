package com.developer.monitor.domain.erpServer.controller;


import com.developer.monitor.common.model.ServerFilePath;
import com.developer.monitor.common.service.CommonService;
import com.developer.monitor.common.service.FileService;
import com.developer.monitor.domain.erpServer.model.MInsertErpSVDiskUsage;
import com.developer.monitor.domain.erpServer.model.MInsertErpSVMain;
import com.developer.monitor.domain.erpServer.model.MInsertErpSVProcChk;
import com.developer.monitor.domain.erpServer.service.erpSVService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
@Slf4j
public class erpSVController {

    @Autowired
    private erpSVService erpService;
    @Autowired
    private FileService fileService;
    @PostMapping(value= "/getErpSVXmlList")
    //@Scheduled(cron = "0 */5 * * * *")
    public void getErpSVXmlList() throws Exception {
        ServerFilePath filePath = new ServerFilePath();
        List<File> fileListFromDir = fileService.getFileFromDir(filePath.erpSVFilePath);
          log.info("fileListFromDir = {}" + fileListFromDir);
        for(File fileName : fileListFromDir){
            log.info("erpSVFileName = {}", fileName);
            erpService.toJsonFromErpSVXmlData(String.valueOf(fileName));
        }
    }
    @RequestMapping(value="/insertErpMain", method={RequestMethod.POST})
    public String insertErpSVMainData(MInsertErpSVMain insertErpSVMain) throws Exception {

        erpService.InsertErpSVMainData(insertErpSVMain);
        return "OK";
    }
    @RequestMapping(value="/insertErpProc", method = {RequestMethod.POST})
    public String insertErpSVProcData(MInsertErpSVProcChk insertErpSVProcChk) throws Exception {

        erpService.InsertErpSVProcData(insertErpSVProcChk);
        return "OK";
    }
    @RequestMapping(value="/insertErpDisk", method = {RequestMethod.POST})
    public String insertErpSVDiskData(MInsertErpSVDiskUsage mInsertErpSVDiskUsage) throws Exception {

        erpService.InsertErpSVDiskData(mInsertErpSVDiskUsage);
        return "OK";
    }




}
