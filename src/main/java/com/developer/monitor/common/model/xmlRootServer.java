package com.developer.monitor.common.model;

import com.developer.monitor.domain.etcServer.model.MXmlGetEtcSVEntity;
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
    private MXmlGetEtcSVEntity[] etcXmlServer;


}
