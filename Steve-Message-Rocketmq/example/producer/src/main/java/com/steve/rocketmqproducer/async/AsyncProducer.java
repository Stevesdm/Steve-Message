package com.steve.rocketmqproducer.async;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: stevejobson
 * @CreateDate: 2018/1/31 上午11:13
 */
@Component
public class AsyncProducer {

    private static final Logger logger = LoggerFactory.getLogger(AsyncProducer.class);

    public void asyncMessage() throws InterruptedException, MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("Steve-message-rocketmq-prodecer");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

            try {
                Message msg = new Message("TopicTest",// topic
                        "TagA",// tag
                        ("Hello RocketMQ ").getBytes()// body,
                );
                producer.send(msg, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println(sendResult.toString());
                    }

                    @Override
                    public void onException(Throwable e) {
                        logger.error("send message error");
                    }
                });
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        producer.shutdown();
    }
}
