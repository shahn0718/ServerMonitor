package com.developer.monitor.infra.sms.repository;

import com.developer.monitor.infra.sms.model.MSmsMember;

public interface smsInfraRepository {

    MSmsMember saveMember(MSmsMember mSmsMember);
}
