package com.igoosd.biz;

import com.igoosd.domain.Program;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ScreenSendServcieTest {

    @Autowired
    private ScreenSendService screenSendService;

    @Test
    public void releaseMedisScreen(){
        Program program = new Program();
        program.setContent("hello,大家好！I am Trustin_Hu.");
       // screenSendService.releaseProgram("e10-a16-00017",program);

    }
}
