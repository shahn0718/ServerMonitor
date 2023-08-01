package com.developer.monitor.domain.admin.controller;

import com.developer.monitor.domain.admin.model.MadminMemberMain;
import com.developer.monitor.domain.admin.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
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
        memberService.joinMember(joinMember);

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
        memberService.findMemberByMail(findMemberByMail.getAdmin_mail());
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
        memberService.findMemberByName(findMemberByName.getAdmin_nm());
        return "getFindUserByName";
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/doFindAllMember", method={RequestMethod.POST})
    public String doFindAllMember() throws Exception{

        memberService.findAllMembers();
        return "doFindAllMember";
    }

    /**
     *
     * @param adminEmpMail
     * @param updateMemberMain
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/doUpdateMember", method={RequestMethod.POST})
    public String doUpdateMember(String adminEmpMail, MadminMemberMain updateMemberMain) throws Exception {

        MadminMemberMain findMemberByMail = new MadminMemberMain();
        findMemberByMail.setAdmin_mail("zzang_gu");

        MadminMemberMain updateMemberInfo = new MadminMemberMain();
        updateMemberInfo.setAdmin_nm("짱구");
        updateMemberInfo.setAdmin_no("920718");
        updateMemberInfo.setAdmin_cellno("01044774329");
        updateMemberInfo.setAdmin_mail("zzang_gu2");
        memberService.updateMember(findMemberByMail.getAdmin_mail(),updateMemberInfo);

        return "doUpdateMember";
    }

    /**
     *
     * @param adminEmpMail
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/doDeleteMember", method={RequestMethod.POST})
    public String doDeleteMember(String adminEmpMail) throws Exception{

        MadminMemberMain findMemberByMail = new MadminMemberMain();
        findMemberByMail.setAdmin_mail("zzang_gu2");
        memberService.deleteMember(findMemberByMail.getAdmin_mail());

        return "doDeleteMember";
    }


}