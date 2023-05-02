package com.developer.monitor.global.domain;

import com.developer.monitor.domain.etcServer.domain.etcServer;
import jakarta.xml.bind.annotation.XmlAccessType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="server")
@Getter
@ToString
public class xmlRootServer {

    @XmlElement(name="info")
    private etcServer[] etcXmlServer;



}
