package com.steve.cache.demo;

import com.steve.cache.cache.DataSyncEventHandler;
import com.steve.cache.cache.LocalCache;
import com.steve.cache.constants.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: stevejobson
 * @CreateDate: 2018/1/30 上午11:02
 */
@Component
public class DemoSyncEventHandler implements DataSyncEventHandler {

    @Autowired
    private LocalCache localCache;

    @Override
    public String getChannel() {
        return Const.DEMO_REDIS_CHANNEL;
    }

    @Override
    public void handle(Object object) {
        localCache.removeAllDemoCache();
    }
}
