package com.steve.rocketmqproducer;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RocketmqProducerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RocketmqProducerApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		DefaultMQProducer producer = new DefaultMQProducer("Steve-message-rocketmq-prodecer");
		producer.setNamesrvAddr("localhost:9876");
		producer.start();

		for (int i = 0; i < 1; i++) {
			try {
				Message msg = new Message("TopicTest",// topic
						"TagA",// tag
						("Hello RocketMQ " + i).getBytes()// body
				);
				SendResult sendResult = producer.send(msg);

				System.out.println(sendResult);
				Thread.sleep(300);
			}
			catch (Exception e) {
				e.printStackTrace();
				Thread.sleep(300);
			}
		}

		producer.shutdown();
	}
}
