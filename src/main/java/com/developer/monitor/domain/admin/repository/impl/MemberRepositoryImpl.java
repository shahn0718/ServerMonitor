package com.developer.monitor.domain.admin.repository.impl;

import com.developer.monitor.domain.admin.mapper.MemberMapper;
import com.developer.monitor.domain.admin.model.MadminMemberMain;
import com.developer.monitor.domain.admin.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Slf4j
public class MemberRepositoryImpl implements MemberRepository {

    @Autowired
    private MemberMapper memberMapper;
    private static Map<Long, MadminMemberMain> memberInfo = new HashMap<>();
    private static long adminId;
    @Override
    public MadminMemberMain saveMember(MadminMemberMain madminMemberMain) {
        madminMemberMain.setAdmin_id(adminId);
        memberInfo.put(madminMemberMain.getAdmin_id(), madminMemberMain);
        log.info("memberinfo = {}", memberInfo);
        memberMapper.saveMember(madminMemberMain);
        return madminMemberMain;
    }
    @Override
    public Optional<MadminMemberMain> findByMail(String adminEmpMail) {

        MadminMemberMain findMemberByMail = memberMapper.findMemberByMail(adminEmpMail);
        log.info("findMemberByMail = {}", findMemberByMail);
        return Optional.ofNullable(findMemberByMail);
    }
    @Override
    public Optional<MadminMemberMain> findByName(String adminEmpName) {

        MadminMemberMain findMemberByName = this.memberMapper.findMemberByName(adminEmpName);
        log.info("findMemberByName = {}", findMemberByName);
        return memberInfo.values().stream()
                .filter(memberInfo -> memberInfo.getAdmin_nm().equals(adminEmpName))
                .findAny();
    }
    @Override
    public List<MadminMemberMain> findAll() {
        List<MadminMemberMain> findAllMembers = memberMapper.findAllMember();
        log.info("findAllMembers={}",findAllMembers);
        return findAllMembers;
    }
    @Override
    public void updateMember(String adminEmpMail, MadminMemberMain updateMemberMain) {
        /**
         *  update 멤버가 있는 경우, 없는 경우 고려하기
         *  우선, 있다는 가정하에 작업 후 → 추가 수정 예정
         */
        MadminMemberMain doUpdateMember = findByMail(adminEmpMail).get();
        System.out.println("doUpdateMember_adminRepositoryImpl = " + doUpdateMember);
        doUpdateMember.setAdmin_id(doUpdateMember.getAdmin_id());
        doUpdateMember.setAdmin_nm(updateMemberMain.getAdmin_nm());
        doUpdateMember.setAdmin_no(updateMemberMain.getAdmin_no());
        doUpdateMember.setAdmin_cellno(updateMemberMain.getAdmin_cellno());
        doUpdateMember.setAdmin_mail(updateMemberMain.getAdmin_mail());

        memberMapper.updateMember(doUpdateMember);
    }

    @Override
    public void deleteMember(String adminEmpMail) {
        MadminMemberMain doDeleteMember = findByMail(adminEmpMail).get();
        log.info("deleteMeberMain={}", doDeleteMember);
        memberMapper.deleteMember(doDeleteMember);
    }
}
