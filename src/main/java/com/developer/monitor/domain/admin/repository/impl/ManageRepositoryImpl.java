package com.developer.monitor.domain.admin.repository.impl;

import com.developer.monitor.domain.admin.mapper.ManageMapper;
import com.developer.monitor.domain.admin.model.MmanageMain;
import com.developer.monitor.domain.admin.repository.ManageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class ManageRepositoryImpl implements ManageRepository {

    @Autowired
    private ManageMapper manageMapper;
    private static Map<Long, MmanageMain> manageInfo = new HashMap<>();
    private static long manageId;

    @Override
    public MmanageMain createManageList(MmanageMain mmanageMain) {

        mmanageMain.setManage_id(manageId);
        manageInfo.put(mmanageMain.getManage_id(), mmanageMain);
        log.info("manageInfo={}", manageInfo);
        manageMapper.createManageList(mmanageMain);
        return mmanageMain;
    }

    @Override
    public Optional<MmanageMain> findManageListBySV(String manageSVCd) {

        MmanageMain findManageListBySV = manageMapper.findManageListBySV(manageSVCd);
        log.info("findManageListBySV={}", findManageListBySV);
        return Optional.ofNullable(findManageListBySV);
    }

    @Override
    public Optional<MmanageMain> findManageListByEmpNo(String adminEmpNo) {

        MmanageMain findManageListByEmpNo = manageMapper.findManageListByEmpNo(adminEmpNo);
        log.info("findManageListByEmpNo = {}", findManageListByEmpNo);
        return manageInfo.values().stream()
                .filter(manageInfo -> manageInfo.getManage_id().equals(adminEmpNo))
                .findAny();
    }
    @Override
    public Optional<MmanageMain> findManageListByIp(String adminEmpNo, String manageSVIp) {

        MmanageMain findManageListByIp = manageMapper.findManageListByIp(adminEmpNo, manageSVIp);
        log.info("findManageListByIp={}", findManageListByIp);
        return Optional.ofNullable(findManageListByIp);
    }

    @Override
    public Optional<MmanageMain> findManageListByInfo(String adminEmpNo, String manageSVCd) {
        MmanageMain findManageListByInfo = manageMapper.findManageListByInfo(adminEmpNo, manageSVCd);
        log.info("findManageListByInfo = {}", findManageListByInfo);
        return Optional.ofNullable(findManageListByInfo);
    }

    @Override
    public List<MmanageMain> findAllManageList() {

        List<MmanageMain> findAllManageList = manageMapper.findAllManageList();
        log.info("findAllManageList={}", findAllManageList);
        return findAllManageList;
    }

    /**
     *
     * @param adminEmpNo
     * @param manageSVCd
     * @param updateManageMain → update 될 내용 (변경 후 내용)
     */
    @Override
    public void updateManageList(String adminEmpNo, String manageSVCd, MmanageMain updateManageMain) {

        MmanageMain doUpdateManageList = findManageListByInfo(adminEmpNo, manageSVCd).get();

        System.out.println("doUpdateManageList = " + doUpdateManageList);
        log.info("adminEmpNo={}", adminEmpNo);
        log.info("manageSVCd={}", manageSVCd);

        doUpdateManageList.setManage_id(doUpdateManageList.getManage_id());
        doUpdateManageList.setManage_ip(updateManageMain.getManage_ip());
        doUpdateManageList.setManage_cd(updateManageMain.getManage_cd());
        doUpdateManageList.setAdmin_no(updateManageMain.getAdmin_no());

        manageMapper.updateManageList(doUpdateManageList);

    }

    @Override
    public void deleteManageList(String adminEmpNo, String manageSVIp) {
        MmanageMain doDeleteManageList = findManageListByIp(adminEmpNo, manageSVIp).get();
        log.info("doDeleteManageList={}" , doDeleteManageList);
        manageMapper.deleteManageList(doDeleteManageList);
    }
}
