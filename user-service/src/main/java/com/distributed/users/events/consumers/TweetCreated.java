package com.distributed.users.events.consumers;

import com.distributed.users.Entites.User;
import com.distributed.users.events.EVENTS;
import com.distributed.users.repostories.IUserRepository;
import com.rabbitmq.client.DeliverCallback;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

@Service
public class TweetCreated extends EventConsumer {

    @Autowired
    IUserRepository userRepository;

    @Override
    public void eventConsume() throws IOException, TimeoutException {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String messageBody = new String(delivery.getBody(), "UTF-8");
            JSONObject json = new JSONObject(messageBody);
            Optional<User> userOptional = this.userRepository.findById((json.getInt("userId")));

            if(userOptional.isPresent()){
                User user = userOptional.get();
                user.setTweetsNumber((user.getTweetsNumber() + 1));
                this.userRepository.save(user);
            }

            System.out.println(" [x] TweetCreated Received '" + messageBody + "'");
        };

        this.consume(EVENTS.TWEET_CREATED,deliverCallback);
    }


}
