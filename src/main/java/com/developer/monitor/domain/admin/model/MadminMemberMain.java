package com.developer.monitor.domain.admin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
public class MadminMemberMain {

    private Long admin_id;
    private String admin_nm;
    private String admin_no;
    private String admin_mail;
    private String admin_cellno;
    private String adminSysCd;

}
