package com.developer.monitor.domain.admin.controller;


import com.developer.monitor.domain.admin.model.MmanageMain;
import com.developer.monitor.domain.admin.service.ManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ManageController {

    private final ManageService manageService;

    @Autowired
    public ManageController(ManageService manageService){
        this.manageService = manageService;
    }

    @RequestMapping(value="/getNewManage", method={RequestMethod.POST})
    public String getNewManage(MmanageMain mmanageMain) throws Exception{

        MmanageMain getNewManageList = new MmanageMain();
        getNewManageList.setManage_id(1L);
        getNewManageList.setManage_cd("ERP");
        getNewManageList.setManage_ip("10.210.1.152");
        getNewManageList.setAdmin_no("C0190005");
        log.info("getNewManageList = {}",getNewManageList);
        manageService.createManageList(getNewManageList);

        return "/getNewManage";

    }

}
