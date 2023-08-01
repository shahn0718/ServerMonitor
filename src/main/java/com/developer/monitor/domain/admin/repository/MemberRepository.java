package com.developer.monitor.domain.admin.repository;

import com.developer.monitor.domain.admin.model.MadminMemberMain;

import java.util.List;
import java.util.Optional;


public interface MemberRepository {
    MadminMemberMain saveMember(MadminMemberMain madminMemberMain);
    Optional<MadminMemberMain> findByMail(String adminEmpMail);
    Optional<MadminMemberMain> findByName(String adminEmpName);
    void updateMember(String adminEmpMail, MadminMemberMain updateMemberMain);
    void deleteMember(String adminEmpMail);
    List<MadminMemberMain> findAll();
}
