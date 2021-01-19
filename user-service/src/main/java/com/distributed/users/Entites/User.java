package com.distributed.users.Entites;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id ;

    @Column(name = "name")
    private String name;

    @Column(name = "TweetsNumber")
    private Integer TweetsNumber = 0;
}
