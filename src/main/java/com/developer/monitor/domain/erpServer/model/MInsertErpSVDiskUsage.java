package com.developer.monitor.domain.erpServer.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MInsertErpSVDiskUsage {
    /**
     * <disk_usage>/data,1</disk_usage>
     * <disk_usage>NameSpace(CD), Usage</disk_usage>
     *
     */
    private int erpSVDiskId;
    private int erpSVId;
    private String erpSVDiskCd;
    private String erpSVDiskUsage;

}
