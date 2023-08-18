package com.developer.monitor.domain.admin.mapper;


import com.developer.monitor.domain.admin.model.MadminMemberMain;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    void saveMember(MadminMemberMain madminMemberMain);
    MadminMemberMain findMemberByMail(String adminEmpMail);
    MadminMemberMain findMemberByName(String adminEmpName);
    void updateMember(MadminMemberMain updateMemberMain);
    void deleteMember(MadminMemberMain deleteMemberMain);
    List<MadminMemberMain> findAllMember();
}
