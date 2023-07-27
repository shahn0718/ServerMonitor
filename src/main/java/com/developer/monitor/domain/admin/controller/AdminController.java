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

    /**
     *
     * @param madminMemberMain
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getJoinUser", method = {RequestMethod.POST})
    public String getJoinUser(MadminMemberMain madminMemberMain) throws Exception {

        MadminMemberMain joinMember = new MadminMemberMain();
        joinMember.setAdmin_no("C0190005");
        joinMember.setAdmin_nm("안상형");
        joinMember.setAdmin_mail("shahn0718");
        joinMember.setAdmin_cellno("01031984329");
        log.info("joinMember = {}", joinMember);
        adminService.joinMember(joinMember);

        return "getJoinUser";
    }
    /**
     *
     * @param madminMemberMain
     * @return
     * @throws Exception
     */

    @RequestMapping(value="/getFindUserByMail", method = {RequestMethod.POST})
    public String getFindUserByMail(MadminMemberMain madminMemberMain) throws Exception{

        MadminMemberMain findMemberByMail = new MadminMemberMain();
        findMemberByMail.setAdmin_mail("shahn0718");
        adminService.findMemberByMail(findMemberByMail.getAdmin_mail());
        return "getFindUserByMail";
    }
    /**
     *
     * @param madminMemberMain
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/getFindUserByName", method={RequestMethod.POST})
    public String getFindUserByName(MadminMemberMain madminMemberMain) throws Exception{

        MadminMemberMain findMemberByName = new MadminMemberMain();
        findMemberByName.setAdmin_nm("홍길동");
        adminService.findMemberByName(findMemberByName.getAdmin_nm());
        return "getFindUserByName";
    }

    @RequestMapping(value="/doUpdateMember", method={RequestMethod.POST})
    public String doUpdateMember(String adminEmpMail, MadminMemberMain updateMemberMain) throws Exception {

        MadminMemberMain findMemberByMail = new MadminMemberMain();
//        findMemberByMail.setAdmin_mail("shahn0718");
//        adminService.findMemberByMail(findMemberByMail.getAdmin_mail());
        String empMail = "shahn0718";

        MadminMemberMain updateMemberInfo = new MadminMemberMain();
        updateMemberInfo.setAdmin_nm("짱구");
        updateMemberInfo.setAdmin_no("920718");
        updateMemberInfo.setAdmin_cellno("01044774329");
        updateMemberInfo.setAdmin_mail("zzang_gu");
        adminService.updateMember(empMail,updateMemberInfo);

        return "doUpdateMember";
    }

}