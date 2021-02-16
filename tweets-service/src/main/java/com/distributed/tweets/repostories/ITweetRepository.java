package com.distributed.tweets.repostories;
import com.distributed.tweets.Entites.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Repository
@Service
public interface ITweetRepository extends JpaRepository<Tweet, Integer> {

}
