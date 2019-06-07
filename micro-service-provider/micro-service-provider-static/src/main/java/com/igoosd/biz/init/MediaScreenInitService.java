package com.igoosd.biz.init;

import com.igoosd.biz.ScreenInitProperties;
import com.igoosd.domain.ControlCard;
import com.igoosd.domain.Program;
import com.igoosd.domain.Screen;
import com.igoosd.enumeration.ScreenTypeEnum;
import com.igoosd.service.ProgramService;
import com.igoosd.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 多媒体屏幕初始化操作
 */
@Service
public class MediaScreenInitService extends AbsScreenInitService {

    @Autowired
    private ScreenInitProperties screenInitProperties;

    @Autowired
    private ProgramService programService;


    @Override
    public ScreenTypeEnum getScreenType() {
        return ScreenTypeEnum.MEDIA;
    }

    /**
     * @param screenList
     * @param controlCard
     * @return
     */
    @Override
    protected String getInitUrl(List<Screen> screenList, ControlCard controlCard) {
        Assert.isTrue(screenList != null && screenList.size() == 1, "一个控制卡只能绑定一个多媒体屏，控制卡code：" + controlCard.getCode());
        Screen screen = screenList.get(0);
        Program program = programService.getLatestReleaseProgram(screen.getId());
        String url = screenInitProperties.getDomain() + "content/mediaPage?width=" + screen.getWidth() + "&height=" + screen.getHeight();
        if (null != program) {
            url += "&programId=" + program.getId();
        }
        return url;
    }

}
