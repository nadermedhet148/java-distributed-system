package com.distributed.users;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties()
public class Configuration {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name ;
    }
}
