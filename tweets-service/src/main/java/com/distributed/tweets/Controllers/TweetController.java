package com.distributed.tweets.Controllers;

import com.distributed.tweets.Controllers.DTO.CreateTweetDto;
import com.distributed.tweets.Entites.Tweet;
import com.distributed.tweets.events.EVENTS;
import com.distributed.tweets.events.EventPubliser;
import com.distributed.tweets.repostories.ITweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController()
@RefreshScope
@RequestMapping(value = "/tweets")
public class TweetController {
    @Autowired
    private ITweetRepository tweetRepository;

    @Autowired
    private EventPubliser eventPubliser;



    @PostMapping(value = "")
    public Tweet createTweet(@RequestHeader("userId") Integer userId , @RequestBody CreateTweetDto body) throws IOException, TimeoutException {
        Tweet tweet = new Tweet();
        tweet.setContent(body.getContent());
        tweet.setTitle(body.getTitle());
        tweet.setUserId(userId);
        this.tweetRepository.save(tweet);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId" , userId);

        this.eventPubliser.publish(EVENTS.TWEET_CREATED , jsonObject.toString());
        return tweet;
    }


}
