package com.developer.monitor.domain.gwServer.controller;


import com.developer.monitor.common.model.ServerFilePath;
import com.developer.monitor.common.service.CommonService;
import com.developer.monitor.domain.gwServer.service.gwSVService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
@Slf4j
public class gwSVController {

    @Autowired
    private gwSVService gwService;
    @Autowired
    private CommonService cmnService;

    @PostMapping("/getGwSVXmlList")
    //@Scheduled(cron = "0 */5 * * * *")
    @Scheduled(cron = "30 * * * * *")
    public void getGwSVXmlList() throws Exception{
        ServerFilePath filePath = new ServerFilePath();
        List<File> fileListFromDir = cmnService.getFileFromDir(filePath.gwSVFilePath);
        for(File fileName : fileListFromDir){
            System.out.println(fileName + " " + " GW");
            gwService.toJsonFromGwSVXmlData(String.valueOf(fileName));
        }
    }
}
