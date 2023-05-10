package com.developer.monitor.domain.etcServer.service.impl;


import com.developer.monitor.domain.etcServer.model.etcSVEntity;
import com.developer.monitor.domain.etcServer.service.etcSVService;
import com.developer.monitor.global.model.xmlRootServer;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service("etcSVService")
public class etcSVServiceImpl implements etcSVService {

    @Override
    public List<etcSVEntity> getEtcSVData() throws Exception, JAXBException {

        FileInputStream fileInputStream = new FileInputStream("");
        JAXBContext jaxbContext = JAXBContext.newInstance(xmlRootServer.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        xmlRootServer xmlRootServerData = (xmlRootServer) unmarshaller.unmarshal(fileInputStream);
        fileInputStream.close();

        List<etcSVEntity> etcSVData = stream(xmlRootServerData.getEtcXmlServer()).collect(Collectors.toList());

        return etcSVData;

    }


}
