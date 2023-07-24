package com.developer.monitor.domain.admin.repository;

import com.developer.monitor.domain.admin.model.MadminMemberMain;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface AdminRepository {
    MadminMemberMain saveMember(MadminMemberMain madminMemberMain);
    MadminMemberMain findByLoginId(String adminEmpMail);
    Optional<MadminMemberMain> findByName(String adminEmpMail);
    void updateMember(String adminEmpMail, MadminMemberMain updateMemberMain);
    void deleteMember(String adminEmpMail);
    List<MadminMemberMain> findAll();
}
