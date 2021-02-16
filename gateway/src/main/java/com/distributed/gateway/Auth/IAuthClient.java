package com.distributed.gateway.Auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "auth", url = "http://localhost:8080")
public interface IAuthClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    User getUser(@RequestHeader("Authorization") String Authorization);

}