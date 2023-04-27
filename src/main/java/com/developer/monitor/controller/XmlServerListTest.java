package com.developer.monitor.controller;

import com.developer.monitor.domain.xmlServerData;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
