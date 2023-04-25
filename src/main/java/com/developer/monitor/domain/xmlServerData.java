package com.developer.monitor.domain;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter @Setter
@ToString
public class xmlServerData {

    @XmlElement(name="hostname")
    private String hostname;
    @XmlElement(name="datetime")
    private Date datetime;
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






}
