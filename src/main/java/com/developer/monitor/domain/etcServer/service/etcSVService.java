package com.developer.monitor.domain.etcServer.service;

import com.developer.monitor.domain.etcServer.model.etcSVEntity;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.util.List;

public interface etcSVService {

    List<etcSVEntity> getEtcSVData() throws Exception, JAXBException;
}
