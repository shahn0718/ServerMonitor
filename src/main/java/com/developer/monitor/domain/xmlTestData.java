package com.developer.monitor.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
@Getter @Setter
@ToString
public class xmlTestData {

    @XmlElement(name="model")
    private String model;
    @XmlElement(name="price")
    private int price;
    @XmlElement(name="firmware")
    private String firmware;
}
