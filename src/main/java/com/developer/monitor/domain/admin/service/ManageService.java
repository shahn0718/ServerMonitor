package com.developer.monitor.domain.admin.service;

import com.developer.monitor.domain.admin.model.MmanageMain;
import com.developer.monitor.domain.admin.repository.ManageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ManageService {

    private final ManageRepository manageRepository;
    @Autowired
    public ManageService(ManageRepository manageRepository){
        this.manageRepository = manageRepository;
    }

    public Long createManageList(MmanageMain mmanageMain){
        log.info("createManageList={}", mmanageMain);
        manageRepository.createManageList(mmanageMain);
        return mmanageMain.getManage_id();
    }
    public Optional<MmanageMain> findManagerBySV(String manageSVCd){
        System.out.println("manageSVCd in manageService = " + manageSVCd);
        return manageRepository.findManageListBySV(manageSVCd);
    }
    public Optional<MmanageMain> findManagerByNo(String adminEmpNo){
        System.out.println("adminEmpNo in manageService = " + adminEmpNo);
        return manageRepository.findManageListByEmpNo(adminEmpNo);
    }
    public List<MmanageMain> findManagerList(){
        return manageRepository.findAllManageList();
    }
    public void updateManageList(String adminEmpNo, String manageSVCd, MmanageMain updateManageMain){
        System.out.println("adminEmpNo in manageService = " + adminEmpNo);
        System.out.println("manageSVCd in manageService = " + manageSVCd);
        log.info("updateManageMain={}",updateManageMain);
        manageRepository.updateManageList(adminEmpNo,manageSVCd,updateManageMain);
    }
    public void deleteManageList(String adminEmpNo, String manageSVCd){
        System.out.println("adminEmpNo in manageService = " + adminEmpNo);
        System.out.println("manageSVCd in manageService = " + manageSVCd);
        manageRepository.deleteManageList(adminEmpNo, manageSVCd);
    }


}
