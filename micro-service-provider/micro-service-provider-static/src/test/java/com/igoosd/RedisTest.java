package com.igoosd;

import com.igoosd.util.AMapUtil;
import com.igoosd.util.LngLat;
import com.igoosd.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void ConnectTest() {
        redisUtil.set("a", "123", 1000);
        System.out.println(redisUtil.get("a"));
    }

    @Test
    public void ttlTest() {
//        System.out.println(redisUtil.getTtl("a"));

        LngLat start = new LngLat(121.5273285, 31.21515044);
        LngLat end = new LngLat(122.9273285, 32.31515044);
        System.out.println("===================================" + AMapUtil.calculateLineDistance(start, end));
    }

}
