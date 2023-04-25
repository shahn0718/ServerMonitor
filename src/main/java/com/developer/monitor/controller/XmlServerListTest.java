package com.developer.monitor.controller;

import com.developer.monitor.domain.xmlServerData;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="server")
@Getter @Setter @ToString
public class XmlServerListTest {

    @XmlElement(name="info")
    private xmlServerData[] serverData;
}
