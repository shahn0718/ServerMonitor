package com.developer.monitor.domain.admin.repository.impl;

import com.developer.monitor.domain.admin.mapper.AdminMapper;
import com.developer.monitor.domain.admin.model.MadminMemberMain;
import com.developer.monitor.domain.admin.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Slf4j
public class AdminRepositoryImpl implements AdminRepository {


    @Autowired
    private AdminMapper adminMapper;
    private static Map<Long, MadminMemberMain> memberInfo = new HashMap<>();
    private static long adminId;

    @Override
    public MadminMemberMain saveMember(MadminMemberMain madminMemberMain) {

        madminMemberMain.setAdminId(adminId);
        memberInfo.put(madminMemberMain.getAdminId(), madminMemberMain);
        log.info("memberinfo = {}", memberInfo);
        adminMapper.saveMember(madminMemberMain);
        return madminMemberMain;
    }

    @Override
    public Optional<MadminMemberMain> findByLoginId(String adminEmpMail) {


        System.out.println("memberInfo.get(adminEmpMail) RepositoryImpl = " + adminEmpMail);
        MadminMemberMain member = adminMapper.findMember(adminEmpMail);
        String adminEmpNo = member.getAdminEmpNo();
        System.out.println("adminEmpNo = " + adminEmpNo);

        return Optional.ofNullable(memberInfo.get(adminEmpMail));
    }

    @Override
    public Optional<MadminMemberMain> findByName(String adminEmpName) {
        return memberInfo.values().stream()
                .filter(memberInfo -> memberInfo.getAdminEmpName().equals(adminEmpName))
                .findAny();
    }

    @Override
    public void updateMember(String adminEmpMail, MadminMemberMain updateMemberMain) {

        Optional<MadminMemberMain> findMember = findByLoginId(adminEmpMail);


//        findMember.setAdminEmpName(updateMemberMain.getAdminEmpName());
//        findMember.setAdminEmpCellNo(updateMemberMain.getAdminEmpCellNo());
//        findMember.setAdminEmpMail(updateMemberMain.getAdminEmpMail());
//        findMember.setAdminSysCd(updateMemberMain.getAdminSysCd());

    }

    @Override
    public void deleteMember(String adminEmpMail) {
//        MadminMemberMain findMember= findByLoginId(adminEmpMail);
//        memberInfo.remove(findMember.getAdminId());
    }

    @Override
    public List<MadminMemberMain> findAll() {
        return new ArrayList<>(memberInfo.values());
    }
}
