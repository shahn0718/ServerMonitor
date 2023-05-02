package com.developer.monitor.domain.erpServer.controller;

import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.IOException;

import static java.util.Arrays.stream;

@Component
@Log
public class ScheduleTaskTest {

    //@Scheduled(fixedDelay= 10000)
    @Scheduled(cron = "0 */5 * * * *")
    public void runEvery10Sec() throws JAXBException, IOException {
        log.info("runEvery10Sec");

        //Given
        FileInputStream fileInputStream = new FileInputStream("C:\\upIt\\monitor\\monitor\\xmlTestFile/serverinfo.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlServerListTest.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        //When
        XmlServerListTest xmlServerListTest = (XmlServerListTest) unmarshaller.unmarshal(fileInputStream);
        fileInputStream.close();

        //Then
       //
//        System.out.println("serverData = " + serverData);
//        Map<String, String> host = serverData.stream()
//                .collect(Collectors.toMap(p->"hostname", hostname -> hostname.getHostname()));
    }
}
