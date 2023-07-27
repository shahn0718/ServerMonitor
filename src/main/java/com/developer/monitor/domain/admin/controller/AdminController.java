package com.developer.monitor.domain.admin.controller;

import com.developer.monitor.domain.admin.model.MadminMemberMain;
import com.developer.monitor.domain.admin.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
public class AdminController{

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @RequestMapping(value = "/getJoinUser", method = {RequestMethod.POST})
    public String getJoinUser(MadminMemberMain madminMemberMain) throws Exception {

        MadminMemberMain joinMember = new MadminMemberMain();
        joinMember.setAdminEmpNo("C0190005");
        joinMember.setAdminEmpName("안상형");
        joinMember.setAdminEmpMail("shahn0718");
        joinMember.setAdminEmpCellNo("01031984329");
        log.info("joinMember = {}", joinMember);
        adminService.joinMember(joinMember);

        return "OK";
    }

    @RequestMapping(value="/getFindUser", method = {RequestMethod.POST})
    public String getFindUser(MadminMemberMain madminMemberMain) throws Exception{

        MadminMemberMain findMember = new MadminMemberMain();
        findMember.setAdminEmpMail("shahn0718");

        String findAdminEmpMail= "shahn0718";

//        log.info("findMember = {}", findMember);

        System.out.println("findMember.getAdminEmpMail() = " + findMember.getAdminEmpMail());
        adminService.findMember(findMember.getAdminEmpMail());
        return "OK";
    }

}