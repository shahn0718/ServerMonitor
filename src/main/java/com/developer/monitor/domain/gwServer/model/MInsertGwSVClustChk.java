package com.developer.monitor.domain.gwServer.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MInsertGwSVClustChk {

    private int gwSVClustId;
    private int gwSVId;
    private String gwSVClustCd;
    private String gwSVClustUsage;
}
