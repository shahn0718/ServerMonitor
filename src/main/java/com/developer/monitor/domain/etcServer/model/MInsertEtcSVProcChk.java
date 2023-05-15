package com.developer.monitor.domain.etcServer.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MInsertEtcSVProcChk {

    private int etcSVProcId;
    private int etcSVId;
    private String etcSVProcCd;
    private String etcSVProcChk;
}
