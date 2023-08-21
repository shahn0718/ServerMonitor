package com.developer.monitor.infra.sms.service;

import com.developer.monitor.infra.sms.model.MSmsMember;
import com.developer.monitor.infra.sms.repository.smsInfraRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class smsInfraService {

    private final smsInfraRepository smsRepository;

    @Autowired
    public smsInfraService(smsInfraRepository smsRepository){
        this.smsRepository = smsRepository;
    }

    public Long joinMember(MSmsMember mSmsMember){
        log.info("joinMember = {}", mSmsMember);
        smsRepository.saveMember(mSmsMember);
        return mSmsMember.getTest_userId();
    }


}
