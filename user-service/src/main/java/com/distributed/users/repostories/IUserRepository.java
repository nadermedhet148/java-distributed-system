package com.distributed.users.repostories;
import com.distributed.users.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Repository
@Service
public interface IUserRepository  extends JpaRepository<User, Integer> {
    Optional<User> findOneByName(String name);

}
