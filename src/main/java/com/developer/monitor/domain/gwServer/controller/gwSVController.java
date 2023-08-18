package com.developer.monitor.domain.gwServer.controller;


import com.developer.monitor.common.model.ServerFilePath;
import com.developer.monitor.common.service.CommonService;
import com.developer.monitor.common.service.FileService;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVClustChk;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVDiskUsage;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVMain;
import com.developer.monitor.domain.gwServer.model.MInsertGwSVProcChk;
import com.developer.monitor.domain.gwServer.service.gwSVService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class gwSVController {

    @Autowired
    private gwSVService gwService;
    @Autowired
    private FileService fileService;

    @PostMapping("/getGwSVXmlList")
    //@Scheduled(cron = "0 */5 * * * *")
    //@Scheduled(cron = "30 * * * * *")
    public void getGwSVXmlList() throws Exception{
        /**
         *  toJsonFromGwSVXmlData 여기에 insertMain / insertDisk / insertProc / insertClust 포함.
         */
        ServerFilePath filePath = new ServerFilePath();
        List<File> fileListFromDir = this.fileService.getFileFromDir(filePath.gwSVFilePath);
        for(File fileName : fileListFromDir){
            log.info("gwSVFileName = {}", fileName);
            gwService.toJsonFromGwSVXmlData(String.valueOf(fileName));
        }
    }

    @RequestMapping(value="/insertGwMain", method={RequestMethod.POST})
    public String insertGwSVMainData(MInsertGwSVMain mInsertGwSVMain) throws Exception {

        gwService.InsertGwSVMainData(mInsertGwSVMain);

        return "OK";
    }

    @RequestMapping(value="/insertGwProc", method={RequestMethod.POST})
    public String insertGwSVProcData(MInsertGwSVProcChk mInsertGwSVProcChk) throws Exception{

        gwService.InsertGwSVProcData(mInsertGwSVProcChk);
        return "OK";
    }

    @RequestMapping(value="/insertGwDisk", method={RequestMethod.POST})
    public String insertGwSVDiskData(MInsertGwSVDiskUsage mInsertGwSVDiskUsage) throws Exception{

        gwService.InsertGwSVDiskData(mInsertGwSVDiskUsage);
        return "OK";
    }

    @RequestMapping(value="/insertGwClust", method={RequestMethod.POST})
    public String insertGwSVClustData(MInsertGwSVClustChk mInsertGwSVClustChk) throws Exception{

        gwService.InsertGwSVClustData(mInsertGwSVClustChk);
        return "OK";
    }
    @RequestMapping(value = "/getCfgList", method = {RequestMethod.POST})
    public String getFileList() throws Exception {


        ServerFilePath cfgFilePath = new ServerFilePath();
        List<File> fileFromDir = fileService.getFileFromDir(cfgFilePath.serverCfgFilePath);
        List<String> arrayList = new ArrayList<>();
        for (File fileName : fileFromDir) {
            System.out.println("fileName = " + fileName);
            arrayList = fileService.makeListFromDir(fileName);
        }

//        HashMap<String,String> defaultMap = new HashMap<>();
//        for(String data: arrayList){
//            String[] array = data.split("=");
//            defaultMap.put(array[0],array[1]);
//        }
//
//
//        System.out.println("defaultMap = " + defaultMap);
//        System.out.println("defaultMapKeySet = " + defaultMap.keySet());


//        String file = "C:\\upIt\\monitor\\monitor\\xmlSVCfgFile\\gw-node4.cfg";
//        FileInputStream fileInputStream = new FileInputStream(file);
//
//        String tmp = "";
//        String data="";
//
//        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        while((tmp = bufferedReader.readLine() )!= null){
//            data += tmp + "\n";
//        }
//
//        System.out.println("data = " + data);
        return "OK";
    }
}
