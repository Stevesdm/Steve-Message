package com.steve.cache.web;

import com.steve.cache.cache.LocalCache;
import com.steve.cache.constants.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: stevejobson
 * @CreateDate: 2018/1/30 上午11:21
 */
@RestController
public class DemoController {

    @Autowired
    private LocalCache localCache;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("/demo")
    public String demo(
    ){
        return localCache.getDemoInfo("steve");
    }

    @RequestMapping("/add")
    public String addDemoCache(){
        Map<String,String> map = new HashMap<>();
        map.put("steve","hahahhha");
        localCache.addLocalDemoCache(map);
        return "success";
    }

    @RequestMapping("/send")
    public String sendMessage(){
        redisTemplate.convertAndSend(Const.DEMO_REDIS_CHANNEL,"demo message");
        return "Success";
    }

}
