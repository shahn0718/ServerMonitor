package com.developer.monitor.domain.erpServer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MInsertErpSVMain {
    /**
     * <server>
     * <info>
     * <hostname>donga-erpmgmt</hostname>
     * <date>2023-06-21</date>
     * <time>13:30:02</time>
     * <osver>CentOS Stream release 8</osver>
     * <ipaddr>10.75.1.65</ipaddr>
     * <cpu_usage>41</cpu_usage>
     * <mem_usage>19</mem_usage>
     * <swap_usage>18</swap_usage>
     * <load_num>1</load_num>
     * <disk_usage>/,35</disk_usage>
     * <disk_usage>/boot,79</disk_usage>
     * <disk_usage>/boot/efi,2</disk_usage>
     * <disk_usage>/home,36</disk_usage>
     * <proc_chk>http,7</proc_chk>
     * <proc_chk>java,6</proc_chk>
     * <proc_chk>mysql,2</proc_chk>
     * </info>
     * </server>
     */

    private int erpSVId;
    private String erpSVCd;
    private String erpSVOs;
    private String erpSVIp;
    private String erpSVCpuUsage;
    private String erpSVMemUsage;
    private String erpSVSwapUsage;
    private String erpSVDateTime;
}
