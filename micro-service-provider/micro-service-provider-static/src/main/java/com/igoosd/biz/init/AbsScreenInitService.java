package com.igoosd.biz.init;

import com.igoosd.biz.ScreenSendService;
import com.igoosd.domain.ControlCard;
import com.igoosd.domain.Screen;
import com.igoosd.enumeration.ScreenTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public abstract class AbsScreenInitService {

    @Autowired
    private ScreenSendService screenSendService;

    public abstract ScreenTypeEnum getScreenType();

    protected abstract String getInitUrl(List<Screen> screenList, ControlCard controlCard);

    public void doInit(List<Screen> screenList, ControlCard controlCard) {
        String url = getInitUrl(screenList, controlCard);
        log.info("当前控制卡：{}的初始化页面url：{}", controlCard.getCode(), url);
        screenSendService.loadUrlForScreen(controlCard.getCode(), url);
    }

}
