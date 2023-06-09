package com.developer.monitor.domain.gwServer.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MInsertGwSVProcChk {

    /**
     * <proc_chk>mySql,2</proc_chk>
     * <proc_chk>NameSpace(CD), Usage</proc_chk>
     *
     */
    private int gwSVProcId;
    private int gwSVId;
    private String gwSVProcCd;
    private String gwSVProcChk;
}
