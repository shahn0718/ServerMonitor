package com.developer.monitor.domain.gwServer.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MInsertGwSVClustChk {

    /**
     * <cluster-chk>diamanti-system, collectd-v0.8-6xxjc, Terminating</cluster-chk>
     * <cluster-chk>NameSpace, PodName, Status</cluster-chk>
     * ** Status - Running or Completed 에서는 생성안되고, 다른경우에만 생성
     */
    private int gwSVClustId;
    private int gwSVId;
    private String gwSVClustCd;
    private String gwSVClustPodName;
    private String gwSVClustStatus;
}
