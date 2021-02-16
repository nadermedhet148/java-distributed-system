package com.distributed.gateway;


import com.distributed.gateway.Auth.IAuthClient;
import com.distributed.gateway.Auth.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

@Component

public class ZuulLoggingFilter extends ZuulFilter{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IAuthClient authClient;

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletRequest request =
                RequestContext.getCurrentContext().getRequest();
        System.out.println(request.getHeader("Authorization"));
        try {

            User user = this.authClient.getUser(request.getHeader("Authorization"));
            requestContext.addZuulRequestHeader("userId" , user.id.toString());
            System.out.println(user.id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }
}