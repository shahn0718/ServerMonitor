package com.developer.monitor.domain.gwServer.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MInsertGwSVMain {

    private int gwSVId;
    private String gwSVCd;
    private String gwSVOs;
    private String gwSVIp;
    private String gwSVCpuUsage;
    private String gwSVMemUsage;
    private String gwSVSwapUsage;
    private String gwSVClustUsage;
    private String gwSVDateTime;
}
