package com.developer.monitor.infra.sms.repository.impl;


import com.developer.monitor.domain.admin.model.MmanageMain;
import com.developer.monitor.infra.sms.mapper.smsMapper;
import com.developer.monitor.infra.sms.model.MSmsMember;
import com.developer.monitor.infra.sms.repository.smsInfraRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
@Slf4j
public class smsInfraRepositoryImpl implements smsInfraRepository {

    @Autowired
    private smsMapper smsMapper;
    private static Map<Long, MSmsMember> msmsInfo = new HashMap<>();
    private static long testId;

    @Override
    public MSmsMember saveMember(MSmsMember mSmsMember) {

        mSmsMember.setTest_userId(testId);
        msmsInfo.put(mSmsMember.getTest_userId(), mSmsMember);
        log.info("msmMemberInfo={}", msmsInfo);
        smsMapper.saveMember(mSmsMember);

        return mSmsMember;
    }
}
