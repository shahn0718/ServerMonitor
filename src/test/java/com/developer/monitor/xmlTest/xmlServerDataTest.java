package com.developer.monitor.xmlTest;

import com.developer.monitor.domain.erpServer.controller.XmlServerListTest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.developer.monitor.domain.xmlServerData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.Arrays.stream;


public class xmlServerDataTest  {

    @Test
    @DisplayName("xml테스트")
    public void serverData() throws JAXBException,IOException {

        //Given
        FileInputStream fileInputStream = new FileInputStream("xmlTestFile/serverInfo.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlServerListTest.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        //When
        XmlServerListTest xmlServerListTest = (XmlServerListTest) unmarshaller.unmarshal(fileInputStream);
        fileInputStream.close();

        //Then
        Assertions.assertNotNull(xmlServerListTest);
        List<xmlServerData> serverData = stream(xmlServerListTest.getServerData())
                .collect(toList());

        System.out.println("serverData = " + serverData);
        System.out.println();
        System.out.println();

        /* host */
//        Map<String, String> host = serverData.stream()
//                .collect(Collectors.toMap(p->"hostname", hostname -> hostname.getHostname()));
//
//        Iterator<xmlServerData> iterServer = serverData.iterator();
//        System.out.println("host = " + host);
//
//        String hostname = host.get("hostname");
//        System.out.println("hostname = " + hostname);

        List<String> procUseTest = serverData.stream()
                .map(x->x.getProChk())
                .flatMap(List::stream)
                .filter(bb->bb.contains(",")).collect(Collectors.toList());

        List<String> diskUseTest = serverData.stream()
                .map(xmlServerData::getDiskUsage)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        //System.out.println("procUseTest.get(2) = " + procUseTest.get(2));

        System.out.println("procUseTest = " + procUseTest);
        System.out.println("diskUseTest = " + diskUseTest);
    }
}