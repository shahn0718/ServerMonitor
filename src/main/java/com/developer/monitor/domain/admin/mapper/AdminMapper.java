package com.developer.monitor.domain.admin.mapper;


import com.developer.monitor.domain.admin.model.MadminMemberMain;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

    void saveMember(MadminMemberMain madminMemberMain);
}
