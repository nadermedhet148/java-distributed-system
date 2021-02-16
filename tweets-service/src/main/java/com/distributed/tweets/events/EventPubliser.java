package com.distributed.tweets.events;

import com.distributed.tweets.events.RMQ.RMQBase;
import com.rabbitmq.client.Channel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Service
public class EventPubliser {
    RMQBase rmqBase = new RMQBase();

    public void publish(String QUEUE_NAME , String message) throws IOException, TimeoutException {
        Channel channel = this.rmqBase.getChannel();
                    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
    }
}
