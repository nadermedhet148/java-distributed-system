package com.distributed.users;

import com.distributed.users.events.consumers.EventConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
@RefreshScope
@EnableDiscoveryClient
@EnableJpaRepositories
public class UsersApplication {

	public static void main(String[] args){
		ConfigurableApplicationContext context = SpringApplication.run(UsersApplication.class, args);
		EventConsumer userCreatedConsumer = (EventConsumer) context.getBean("tweetCreated");
		List<EventConsumer> consumers = Arrays.asList(userCreatedConsumer);
		consumers.forEach(c->{
			try {
				c.eventConsume();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
		});
	}
}
