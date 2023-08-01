package com.developer.monitor.domain.admin.repository;

import com.developer.monitor.domain.admin.model.MmanageMain;

import java.util.List;
import java.util.Optional;
public interface ManageRepository {

    MmanageMain createManageList(MmanageMain mmanageMain);
    Optional<MmanageMain> findManageListBySV(String manageSVCd);
    Optional<MmanageMain> findManageListByEmpNo(String adminEmpNo);
    Optional<MmanageMain> findManageListByInfo(String adminEmpNo, String manageSVCd);
    List<MmanageMain> findAllManageList();
    void updateManageList(String adminEmpNo, String manageSVCd, MmanageMain updateManageMain);
    void deleteManageList(String adminEmpNo, String manageSVCd);



}
