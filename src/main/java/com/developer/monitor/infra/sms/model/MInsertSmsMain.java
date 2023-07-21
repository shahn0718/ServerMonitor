package com.developer.monitor.infra.sms.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MInsertSmsMain {

    private String trSendDate;
    private String trSendStat;
    private String trMsgType;
    private String trPhone;
    private String trCallBack;
    private String trMsg;
    private String trEtc3;


}
