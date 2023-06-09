package com.developer.monitor.domain.etcServer.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MInsertEtcSVProcChk {

    /**
     * <proc_chk>mySql,2</proc_chk>
     * <proc_chk>NameSpace(CD), Usage</proc_chk>
     *
     */
    private int etcSVProcId;
    private int etcSVId;
    private String etcSVProcCd;
    private String etcSVProcChk;
}
