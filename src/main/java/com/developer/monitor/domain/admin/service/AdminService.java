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
        return madminMemberMain.getAdmin_id();
    }
    public Optional<MadminMemberMain> findMemberByMail(String adminEmpMail){
        System.out.println("adminEmpMail in adminService = " + adminEmpMail);
        return adminRepository.findByMail(adminEmpMail);
    }
    public Optional<MadminMemberMain> findMemberByName(String adminEmpName){
        System.out.println("adminEmpName in adminService = " + adminEmpName);
        return adminRepository.findByName(adminEmpName);
    }
    public void updateMember(String adminEmpMail, MadminMemberMain updateMemberMain){
        System.out.println("adminEmpMail in adminService " + adminEmpMail);
        log.info("updateMemberMain = {}", updateMemberMain);
        adminRepository.updateMember(adminEmpMail,updateMemberMain);
    }
    public void deleteMember(String adminEmpMail){
        adminRepository.deleteMember(adminEmpMail);
    }



}
