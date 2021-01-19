package com.distributed.users.Controllers;

import com.distributed.users.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RestController
@RefreshScope
public class UserController {
    @Autowired
    private Configuration configuration;

    @Value("${user.name}")
    private String name;

    @GetMapping("/")
    public String retrieveLimitsFromConfigurations() {
        return  this.name;

    }
}
