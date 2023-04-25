package com.developer.monitor.xmlTest;

import com.developer.monitor.controller.XmlListTest;
import com.developer.monitor.domain.xmlTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;


public class XmlJaxbTest {

    @Test
    @DisplayName("unmarshal 테스트")
    public void jaxbTest() throws JAXBException, IOException {

        //Given
        FileInputStream fileInputStream = new FileInputStream("xmlTestFile/test.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlListTest.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        //When
        XmlListTest xmlListTest = (XmlListTest) unmarshaller.unmarshal(fileInputStream);
        fileInputStream.close();

        //Then
        Assertions.assertNotNull(xmlListTest);

        List<xmlTestData> baseData = stream(xmlListTest.getXmlData()).collect(toList());

        Map<String,Integer> testData = baseData.stream().collect(Collectors.toMap(model->model.getModel(), price->price.getPrice()));

        List<String> firmwareData = stream(xmlListTest.getXmlData()).map(firmware -> firmware.getFirmware()).collect(toList());

        List<String> data = stream(xmlListTest.getXmlData())
                .map(model -> model.getModel()).collect(toList());

        List<Integer> dataPrice = stream(xmlListTest.getXmlData()).map(price -> price.getPrice()).collect(toList());

        System.out.println("baseData = " + baseData);
        System.out.println("testData = " + testData);
        System.out.println("firmwareData = " + firmwareData);
    }

}
