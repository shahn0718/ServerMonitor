package com.developer.monitor.domain.etcServer.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MInsertEtcSVDiskUsage {


    private int etcSVDiskId;
    private int etcSVId;
    private String etcSVDiskCd;
    private String etcSVDiskUsage;
}
