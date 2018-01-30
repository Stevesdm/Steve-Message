package com.steve.cache.config;

import com.steve.cache.constants.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @Description:
 * @Author: stevejobson
 * @CreateDate: 2018/1/30 上午11:49
 */
@Configuration
@Order(100)
public class MessageConfig {


    //配置接收redis消息
    //配置接收主题
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory,MessageListener messageListener){
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        redisMessageListenerContainer.addMessageListener(messageListener,new PatternTopic("channel-*"));
        return redisMessageListenerContainer;
    }



}
