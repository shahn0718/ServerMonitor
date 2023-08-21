package com.developer.monitor.infra.sms.mapper;

import com.developer.monitor.domain.admin.model.MadminMemberMain;
import com.developer.monitor.infra.sms.model.MSmsMember;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface smsMapper {

    void saveMember(MSmsMember mSmsMember);
}
