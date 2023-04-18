package com.developer.monitor.controller;

import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log
public class ScheduleTaskTest {

    @Scheduled(fixedDelay= 10000)
    public void runEvery10Sec(){
        log.info("runEvery10Sec");
    }
}
