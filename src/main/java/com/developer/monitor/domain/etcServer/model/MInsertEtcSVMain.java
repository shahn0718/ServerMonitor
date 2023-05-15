package com.developer.monitor.domain.etcServer.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MInsertEtcSVMain {

    private int etcSVId;
    private String etcSVCd;
    private String etcSVOs;
    private String etcSVIp;
    private String etcSVCpuUsage;
    private String etcSVMemUsage;
    private String etcSVSwapUsage;
    private String etcSVDateTime;


}
