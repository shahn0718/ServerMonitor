package com.developer.monitor.domain.erpServer.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MInsertErpSVProcChk {
    /**
     * <proc_chk>mySql,2</proc_chk>
     * <proc_chk>NameSpace(CD), Usage</proc_chk>
     *
     */
    private int erpSVProcId;
    private int erpSVId;
    private String erpSVProcCd;
    private String erpSVProcChk;
}
