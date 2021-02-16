package com.distributed.users.events.consumers;

import com.distributed.users.events.RMQ.RMQBase;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public abstract class EventConsumer {
    RMQBase rmqBase = new RMQBase();

    public void consume(String QUEUE_NAME , DeliverCallback deliverCallback) throws IOException, TimeoutException {
        Channel channel = this.rmqBase.getChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }

    public abstract void eventConsume() throws IOException, TimeoutException;

}
