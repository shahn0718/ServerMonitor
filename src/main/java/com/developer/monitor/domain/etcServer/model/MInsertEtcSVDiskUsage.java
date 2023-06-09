package com.developer.monitor.domain.etcServer.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MInsertEtcSVDiskUsage {

    /**
     * <disk_usage>/data,1</disk_usage>
     * <disk_usage>NameSpace(CD), Usage</disk_usage>
     *
     */
    private int etcSVDiskId;
    private int etcSVId;
    private String etcSVDiskCd;
    private String etcSVDiskUsage;
}
