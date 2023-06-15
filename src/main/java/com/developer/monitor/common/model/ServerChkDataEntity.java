package com.developer.monitor.common.model;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ServerChkDataEntity {

    private String chkCpuUsage;
    private String chkMemUsage;
    private String chkProcUsage;
    private String chkDiskUsage;
    
}
