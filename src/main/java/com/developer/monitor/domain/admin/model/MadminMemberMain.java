package com.developer.monitor.domain.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString

public class MadminMemberMain {

    private Long adminId;
    private String adminEmpName;
    private String adminEmpNo;
    private String adminEmpMail;
    private String adminEmpCellNo;
    private String adminSysCd;

}