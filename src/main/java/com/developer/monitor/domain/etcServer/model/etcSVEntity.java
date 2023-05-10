package com.developer.monitor.domain.etcServer.model;



import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@ToString
public class etcSVEntity {

    @XmlElement(name="hostname")
    private String hostname;
    @XmlElement(name="date")
    private String datetime;
    @XmlElement(name="time")
    private String timeDate;
    @XmlElement(name="osver")
    private String osVersion;
    @XmlElement(name="ipaddr")
    private String ipAddress;
    @XmlElement(name="cpu_usage")
    private Long cpuUsage;
    @XmlElement(name="mem_usage")
    private Long memUsage;
    @XmlElement(name="swap_usage")
    private Long swapUsage;
    @XmlElement(name="disk_usage")
    private List<String> diskUsage;
    @XmlElement(name="proc_chk")
    private List<String> processChk;
}
