package com.steve.cache.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: stevejobson
 * @CreateDate: 2018/1/30 上午10:47
 */
@Component
public class DataSyncEventListener implements MessageListener {

    @Autowired
    private List<DataSyncEventHandler> dataSyncEventHandlers;

    @Autowired
    private StringRedisSerializer stringRedisSerializer;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println(message.toString());

        String channel = stringRedisSerializer.deserialize(message.getChannel());
        Object object = stringRedisSerializer.deserialize(message.getBody());


        System.out.println(channel);
        System.out.println(object.toString());

        for (DataSyncEventHandler dataSyncEventHandler : dataSyncEventHandlers){
            if (dataSyncEventHandler.getChannel().equals(channel)){
                dataSyncEventHandler.handle(object);
            }
        }

    }
}
