package com.developer.monitor.domain.etcServer.controller;

import com.developer.monitor.domain.etcServer.mapper.etcSVMapper;
import com.developer.monitor.domain.etcServer.model.MXmlGetEtcSVEntity;
import com.developer.monitor.domain.etcServer.service.etcSVService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;


@RestController
@Slf4j
public class etcSVController {

    private etcSVService etcService;
    private etcSVMapper etcSVMapper;

    public etcSVController(etcSVService etcService) {
        this.etcService = etcService;
    }

    @RequestMapping(value="/hello", method={RequestMethod.POST})
    @Scheduled(cron = "0 */5 * * * *")
    public List<MXmlGetEtcSVEntity> getEtcSVDataList() throws Exception {
//        System.out.println("this.etcService.getEtcSVData().toString() = " + etcService.getMainDataFromEtcSVData().toString());
//        System.out.println("this.etcService.getDiskUsagefromEtcSVData().toString() = " + etcService.getDiskDatafromEtcSVData().toString());
//        System.out.println("this.etcService.getDiskUsagefromEtcSVData().toString() = " + etcService.getProcessDatafromEtcSVData().toString());
//
//        log.info("runEvery10Sec");
        return etcService.getMainDataFromEtcSVData();
    }

    @RequestMapping(value="/insertData", method={RequestMethod.POST})
    public String InsertTest(Model model) throws Exception {

        return "OK";
    }


    public List<String> getDiskUsageDataList() throws Exception {
        return etcService.getDiskDatafromEtcSVData();
    }

    public List<String> getProcessChkDataList() throws Exception{
        return etcService.getProcessDatafromEtcSVData();
    }
}
