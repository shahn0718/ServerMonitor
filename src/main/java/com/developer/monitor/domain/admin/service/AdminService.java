package com.developer.monitor.domain.admin.service;

import com.developer.monitor.domain.admin.model.MadminMemberMain;
import com.developer.monitor.domain.admin.repository.AdminRepository;
import com.developer.monitor.domain.admin.repository.impl.AdminRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository = new AdminRepositoryImpl();

    public Long joinMember(MadminMemberMain madminMemberMain){
        adminRepository.saveMember(madminMemberMain);
        return madminMemberMain.getAdminId();
    }

    public Optional<MadminMemberMain> findMember(String adminEmpMail){
        return Optional.ofNullable(adminRepository.findByLoginId(adminEmpMail));
    }

    public void deleteMember(String adminEmpMail){
        adminRepository.deleteMember(adminEmpMail);
    }

    public void updateMember(MadminMemberMain updateMemberMain, String adminEmpMail){
        adminRepository.updateMember(adminEmpMail,updateMemberMain);
    }


}
