package com.developer.monitor.domain.etcServer.controller;

import com.developer.monitor.domain.etcServer.model.etcSVEntity;
import com.developer.monitor.global.model.xmlRootServer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@Controller
public class etcSVController {

    @GetMapping("/hello")
    public Model hello(Model model) throws JAXBException, IOException {

        FileInputStream fileInputStream = new FileInputStream("xmlTestFile/serverInfo.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(xmlRootServer.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        xmlRootServer xmlRootServerList = (xmlRootServer) unmarshaller.unmarshal(fileInputStream);
        fileInputStream.close();

        List<etcSVEntity> serverData = stream(xmlRootServerList.getEtcXmlServer())
                .collect(toList());

        System.out.println("serverData = " + serverData);

        return model.addAttribute("serverData", serverData);

    }
}
