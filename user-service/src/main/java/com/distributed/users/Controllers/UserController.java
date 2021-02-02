package com.distributed.users.Controllers;

import com.distributed.users.Controllers.DTO.CreateUserDto;
import com.distributed.users.Entites.User;
import com.distributed.users.JWT.JwtTokenProvider;
import com.distributed.users.repostories.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import javax.inject.Inject;
import java.util.Optional;

@RestController()
@RefreshScope
@RequestMapping(value = "/users")
@AllArgsConstructor(onConstructor = @__(
        @Inject))
public class UserController {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;



    @PostMapping(value = "")
    public User createUser(@RequestBody CreateUserDto body) {
        Optional<User> userCreatedBefore = this.userRepository.findOneByName(body.getName());

        if(userCreatedBefore.isPresent()) {
            return  null;
        }
        User user = new User();
        user.setName(body.getName());
        this.userRepository.save(user);
        return user;
    }

    @GetMapping(value = "/{name}/token")
    public String getUser(@PathVariable String name) {
        Optional<User> user = this.userRepository.findOneByName(name);

        if(!user.isPresent()) {
            return  null;
        }

        return jwtTokenProvider.createToken(user.get().getName(), user.get().getId());
    }

    @GetMapping(value = "")
    public User getUserData(Authentication authentication) {
        Optional<User> user = this.userRepository.findOneByName(authentication.getName());

        return user.get();
    }

}
