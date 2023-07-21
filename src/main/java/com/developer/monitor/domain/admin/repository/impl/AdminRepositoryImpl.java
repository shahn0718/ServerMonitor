package com.developer.monitor.domain.admin.repository.impl;

import com.developer.monitor.domain.admin.model.MadminMemberMain;
import com.developer.monitor.domain.admin.repository.AdminRepository;

import java.util.*;

public class AdminRepositoryImpl implements AdminRepository {


    private static Map<Long, MadminMemberMain> memberInfo = new HashMap<>();
    private static long adminId = 0L;

    @Override
    public MadminMemberMain saveMember(MadminMemberMain madminMemberMain) {

        madminMemberMain.setAdminId(++adminId);
        memberInfo.put(madminMemberMain.getAdminId(), madminMemberMain);
        return madminMemberMain;
    }

    @Override
    public MadminMemberMain findByLoginId(String adminEmpMail) {
        return memberInfo.get(adminEmpMail);
    }

    @Override
    public Optional<MadminMemberMain> findByName(String adminEmpName) {
        return memberInfo.values().stream()
                .filter(memberInfo -> memberInfo.getAdminEmpName().equals(adminEmpName))
                .findAny();
    }

    @Override
    public void updateMember(String adminEmpMail, MadminMemberMain updateMemberMain) {

        MadminMemberMain findMember = findByLoginId(adminEmpMail);

        findMember.setAdminEmpName(updateMemberMain.getAdminEmpName());
        findMember.setAdminEmpCellNo(updateMemberMain.getAdminEmpCellNo());
        findMember.setAdminEmpMail(updateMemberMain.getAdminEmpMail());
        findMember.setAdminSysCd(updateMemberMain.getAdminSysCd());

    }

    @Override
    public void deleteMember(String adminEmpMail) {
        MadminMemberMain findMember= findByLoginId(adminEmpMail);
        memberInfo.remove(findMember.getAdminId());
    }

    @Override
    public List<MadminMemberMain> findAll() {
        return new ArrayList<>(memberInfo.values());
    }
}
