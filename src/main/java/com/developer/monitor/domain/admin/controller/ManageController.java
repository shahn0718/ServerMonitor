package com.developer.monitor.domain.admin.controller;


import com.developer.monitor.domain.admin.model.MmanageMain;
import com.developer.monitor.domain.admin.service.ManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ManageController {

    private final ManageService manageService;

    @Autowired
    public ManageController(ManageService manageService){
        this.manageService = manageService;
    }

    /**
     *
     * @param mmanageMain
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/getNewManage", method={RequestMethod.POST})
    public String getNewManage(MmanageMain mmanageMain) throws Exception{

        MmanageMain getNewManageList = new MmanageMain();
        getNewManageList.setManage_id(1L);
        getNewManageList.setManage_cd("ERP");
        getNewManageList.setManage_ip("10.210.1.152");
        getNewManageList.setAdmin_no("C0190005");
        log.info("getNewManageList = {}",getNewManageList);
        manageService.createManageList(getNewManageList);

        return "getNewManageList";
    }

    /**
     * getFindManagerListBySV
     * 서버 카테고리로 담당자 리스트 찾기
     *
     * @param mmanageMain
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/getManageListBySV", method = {RequestMethod.POST})
    public String getFindManageListBySV(MmanageMain mmanageMain) throws Exception{

        MmanageMain findManageListBySV = new MmanageMain();
        findManageListBySV.setManage_cd("ERP");
        manageService.findManagerBySV(findManageListBySV.getManage_cd());
        return "getFindManageList";
    }

    /**
     * getFindManageListByEmpNo
     * 사원번호로 담당자 리스트 찾기
     *
     * @param mmanageMain
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/getManageListByNo", method={RequestMethod.POST})
    public String getFindManageListByEmpNo(MmanageMain mmanageMain) throws Exception{

        MmanageMain findManageListByEmpNo = new MmanageMain();
        findManageListByEmpNo.setAdmin_no("C0190005");
        manageService.findManagerByNo(findManageListByEmpNo.getAdmin_no());
        return "getManageListByNo";
    }

    /**
     * getFindManageListByInfo
     * 사원번호, 서버분류로 담당자 리스트 찾기
     * 
     * @param mmanageMain
     * @return
     * @throws Exception
     */

    @RequestMapping(value="/getManageListByInfo", method={RequestMethod.POST})
    public String getFindManageListByInfo(MmanageMain mmanageMain) throws Exception{

        MmanageMain findManageListByInfo = new MmanageMain();
        findManageListByInfo.setAdmin_no("C0190005");
        findManageListByInfo.setManage_cd("ERP");
        manageService.findManageListByInfo(findManageListByInfo.getAdmin_no(), findManageListByInfo.getManage_cd());
        
        return "getManageListByInfo";
    }

    @RequestMapping(value="/doUpdateManageList", method={RequestMethod.POST})
    public String doUpdateManageList(String adminEmpNo, String manageSVCd, MmanageMain updateManageMain) throws Exception{

        MmanageMain findManageListByInfo = new MmanageMain();
        findManageListByInfo.setAdmin_no("C0190005");
        findManageListByInfo.setManage_cd("ERP");


        MmanageMain updateManageListInfo = new MmanageMain();
        updateManageListInfo.setAdmin_no("C0230005");
        updateManageListInfo.setManage_ip("10.210.1.151");
        updateManageListInfo.setManage_cd("GW");
        System.out.println("updateManageListInfo = " + updateManageListInfo);
        manageService.updateManageList(findManageListByInfo.getAdmin_no(),findManageListByInfo.getManage_cd(),updateManageListInfo);

        return "doUpdateManageList";

    }







}
