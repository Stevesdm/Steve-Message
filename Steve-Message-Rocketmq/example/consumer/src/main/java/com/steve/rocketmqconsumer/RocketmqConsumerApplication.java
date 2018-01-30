package com.steve.rocketmqconsumer;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class RocketmqConsumerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RocketmqConsumerApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("Steve-message-rocketmq-consumer");
		consumer.setNamesrvAddr("localhost:9876");

		/**
		 * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
		 * 如果非第一次启动，那么按照上次消费的位置继续消费
		 */
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

		consumer.subscribe("TopicTest", "*");

		consumer.registerMessageListener(new MessageListenerConcurrently() {

			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
															ConsumeConcurrentlyContext context) {
				System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs);
				System.out.println(" Receive Message Size: " + msgs.size());
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});

		consumer.start();

		System.out.println("Consumer Started.");
	}
}
