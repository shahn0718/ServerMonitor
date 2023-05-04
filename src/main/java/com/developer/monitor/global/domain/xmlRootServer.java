package com.developer.monitor.global.domain;

import com.developer.monitor.domain.etcServer.domain.etcServer;
import lombok.Getter;

import lombok.ToString;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="server")
@Getter @ToString
public class xmlRootServer {
    @XmlElement(name="info")
    private etcServer[] etcXmlServer;

}
