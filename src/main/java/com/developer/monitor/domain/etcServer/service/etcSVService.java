package com.developer.monitor.domain.etcServer.service;

import com.developer.monitor.domain.etcServer.model.MXmlGetEtcSVEntity;
import jakarta.xml.bind.JAXBException;

import java.util.List;

public interface etcSVService {


    List<MXmlGetEtcSVEntity> getMainDataFromEtcSVData() throws Exception, JAXBException;
    List<String> getDiskDatafromEtcSVData() throws Exception;
    List<String> getProcessDatafromEtcSVData() throws Exception;
}
