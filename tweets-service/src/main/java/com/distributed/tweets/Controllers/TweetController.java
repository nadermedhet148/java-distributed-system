package com.distributed.tweets.Controllers;

import com.distributed.tweets.Controllers.DTO.CreateTweetDto;
import com.distributed.tweets.Entites.Tweet;
import com.distributed.tweets.repostories.ITweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RestController()
@RefreshScope
@RequestMapping(value = "/tweets")
public class TweetController {
    @Autowired
    private ITweetRepository tweetRepository;



    @PostMapping(value = "")
    public Tweet createTweet(@RequestHeader("userId") Integer userId , @RequestBody CreateTweetDto body) {

        System.out.println(userId);
        Tweet tweet = new Tweet();
        tweet.setContent(body.getContent());
        tweet.setTitle(body.getTitle());
        tweet.setUserId(userId);
        this.tweetRepository.save(tweet);
        return tweet;
    }


}
