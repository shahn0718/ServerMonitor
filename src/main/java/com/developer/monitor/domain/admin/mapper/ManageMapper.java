package com.developer.monitor.domain.admin.mapper;

import com.developer.monitor.domain.admin.model.MmanageMain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManageMapper {

    void createManageList(MmanageMain mmanageMain);
    MmanageMain findManageListBySV(String manageSVCd);
    MmanageMain findManageListByEmpNo(String adminEmpNo);
    MmanageMain findManageListByInfo(String adminEmpNo, String manageSVCd);
    void updateManageList(MmanageMain updateManageMain);
    void deleteManageList(MmanageMain deleteManageMain);
    List<MmanageMain> findAllManageList();




}
