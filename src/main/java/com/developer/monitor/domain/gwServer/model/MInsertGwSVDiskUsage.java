package com.developer.monitor.domain.gwServer.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MInsertGwSVDiskUsage {

    private int gwSVDiskId;
    private int gwSVId;
    private String gwSVDiskCd;
    private String gwSVDiskUsage;
}
