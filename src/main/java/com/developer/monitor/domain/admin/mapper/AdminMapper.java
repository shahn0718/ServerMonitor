package com.developer.monitor.domain.admin.mapper;


import com.developer.monitor.domain.admin.model.MadminMemberMain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminMapper {

    void saveMember(MadminMemberMain madminMemberMain);
    MadminMemberMain findMember(String adminEmpMail);
}
