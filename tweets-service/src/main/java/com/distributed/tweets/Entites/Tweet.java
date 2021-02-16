package com.distributed.tweets.Entites;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "Tweets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Tweet {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id ;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "userId")
    private Integer userId;
}
