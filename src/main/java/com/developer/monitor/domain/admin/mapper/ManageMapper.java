package com.developer.monitor.domain.admin.mapper;

import com.developer.monitor.domain.admin.model.MmanageMain;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManageMapper {

    void createManageList(MmanageMain mmanageMain);
    MmanageMain findManageListBySV(String manageSVCd);
    MmanageMain findManageListByEmpNo(String adminEmpNo);
    MmanageMain findManageListByIp(@Param("admin_no")String adminEmpNo, @Param("manage_ip")String manageSVIp);
    MmanageMain findManageListByInfo(@Param("admin_no")String adminEmpNo, @Param("manage_cd")String manageSVCd);
    void updateManageList(MmanageMain updateManageMain);
    void deleteManageList(MmanageMain deleteManageMain);
    List<MmanageMain> findAllManageList();




}
