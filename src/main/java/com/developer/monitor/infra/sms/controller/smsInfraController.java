package com.developer.monitor.infra.sms.controller;


import com.developer.monitor.domain.admin.model.MadminMemberMain;
import com.developer.monitor.infra.sms.model.MSmsMember;
import com.developer.monitor.infra.sms.service.smsInfraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class smsInfraController {


    private final smsInfraService smsService;

    @Autowired
    public smsInfraController(smsInfraService smsService){
        this.smsService = smsService;
    }

    /**
     * getJoinUser
     *
     * @param mSmsMember
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getSmsUser", method = {RequestMethod.POST})
    public String getSmsUser(MSmsMember mSmsMember) throws Exception {

        MSmsMember joinMember = new MSmsMember();
        joinMember.setTest_userNo("010009990");
        joinMember.setTest_userNm("테스터");
        log.info("joinMember = {}", joinMember);
        smsService.joinMember(joinMember);

        return "getSmsUser";
    }





}
