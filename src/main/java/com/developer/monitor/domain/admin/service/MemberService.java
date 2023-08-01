package com.developer.monitor.domain.admin.service;

import com.developer.monitor.domain.admin.model.MadminMemberMain;
import com.developer.monitor.domain.admin.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Long joinMember(MadminMemberMain madminMemberMain){
        log.info("joinMember = {}", madminMemberMain);
        memberRepository.saveMember(madminMemberMain);
        return madminMemberMain.getAdmin_id();
    }
    public Optional<MadminMemberMain> findMemberByMail(String adminEmpMail){
        System.out.println("adminEmpMail in adminService = " + adminEmpMail);
        return memberRepository.findByMail(adminEmpMail);
    }
    public Optional<MadminMemberMain> findMemberByName(String adminEmpName){
        System.out.println("adminEmpName in adminService = " + adminEmpName);
        return memberRepository.findByName(adminEmpName);
    }
    public List<MadminMemberMain> findAllMembers(){
        return memberRepository.findAll();
    }
    public void updateMember(String adminEmpMail, MadminMemberMain updateMemberMain){
        System.out.println("adminEmpMail in adminService " + adminEmpMail);
        log.info("updateMemberMain = {}", updateMemberMain);
        memberRepository.updateMember(adminEmpMail,updateMemberMain);
    }
    public void deleteMember(String adminEmpMail) {
        System.out.println("adminEmpMail in adminService " + adminEmpMail);
        //log.info 관련 데이터는 adminRepositoryImpl
        memberRepository.deleteMember(adminEmpMail);
    }

}
