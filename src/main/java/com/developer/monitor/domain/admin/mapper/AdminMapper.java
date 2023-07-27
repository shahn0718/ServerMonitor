package com.developer.monitor.domain.admin.mapper;


import com.developer.monitor.domain.admin.model.MadminMemberMain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminMapper {

    void saveMember(MadminMemberMain madminMemberMain);
    MadminMemberMain findMemberByMail(String adminEmpMail);
    MadminMemberMain findMemberByName(String adminEmpName);
    void updateMember(MadminMemberMain updateMemberMain);
    void deleteMember(MadminMemberMain deleteMemberMain);
}
