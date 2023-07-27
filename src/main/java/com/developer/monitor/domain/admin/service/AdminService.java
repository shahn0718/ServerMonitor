package com.developer.monitor.domain.admin.service;

import com.developer.monitor.domain.admin.model.MadminMemberMain;
import com.developer.monitor.domain.admin.repository.AdminRepository;
import com.developer.monitor.domain.admin.repository.impl.AdminRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    public Long joinMember(MadminMemberMain madminMemberMain){

        log.info("joinMember = {}", madminMemberMain);
        adminRepository.saveMember(madminMemberMain);
        return madminMemberMain.getAdminId();
    }

    public Optional<MadminMemberMain> findMember(String adminEmpMail){

        System.out.println("adminEmpMail in Service = " + adminEmpMail);
        return adminRepository.findByLoginId(adminEmpMail);
    }

    public void deleteMember(String adminEmpMail){
        adminRepository.deleteMember(adminEmpMail);
    }

    public void updateMember(MadminMemberMain updateMemberMain, String adminEmpMail){
        adminRepository.updateMember(adminEmpMail,updateMemberMain);
    }


}
