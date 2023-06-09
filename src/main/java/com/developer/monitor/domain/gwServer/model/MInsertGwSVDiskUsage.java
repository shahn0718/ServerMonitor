package com.developer.monitor.domain.gwServer.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MInsertGwSVDiskUsage {

    /**
     * <disk_usage>/data,1</disk_usage>
     * <disk_usage>NameSpace(CD), Usage</disk_usage>
     *
     */
    private int gwSVDiskId;
    private int gwSVId;
    private String gwSVDiskCd;
    private String gwSVDiskUsage;
}
