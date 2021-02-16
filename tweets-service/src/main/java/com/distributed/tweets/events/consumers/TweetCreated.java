package com.distributed.tweets.events.consumers;

import com.distributed.tweets.events.consumers.EventConsumer;
import com.rabbitmq.client.DeliverCallback;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class TweetCreated extends EventConsumer {


    @Override
    public void eventConsume() throws IOException, TimeoutException {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String messageBody = new String(delivery.getBody(), "UTF-8");
            JSONObject json = new JSONObject(messageBody);

//            System.out.println(" [x] Received '" + messageBody + "'");
        };

//        this.consume(EVENTS.CREATED_USER,deliverCallback);
    }


}
