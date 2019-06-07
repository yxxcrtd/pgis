package com.igoosd.biz;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("screen.init")
@Data
public class ScreenInitProperties {

    private int warnNum = 30;
    private String domain;
}
