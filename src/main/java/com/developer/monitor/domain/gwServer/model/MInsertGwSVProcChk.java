package com.developer.monitor.domain.gwServer.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MInsertGwSVProcChk {

    private int gwSVProcId;
    private int gwSVId;
    private String gwSVProcCd;
    private String gwSVProcChk;
}
