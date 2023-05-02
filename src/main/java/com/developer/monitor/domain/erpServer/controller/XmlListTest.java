package com.developer.monitor.domain.erpServer.controller;


import com.developer.monitor.domain.xmlTestData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="list")
@Getter @Setter @ToString
public class XmlListTest {

    @XmlElement(name="test-phone")
    private xmlTestData[] xmlData;

}
