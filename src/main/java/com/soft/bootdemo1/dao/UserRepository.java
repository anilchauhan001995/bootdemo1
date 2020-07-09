package com.soft.bootdemo1.dao;

import com.soft.bootdemo1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Dell on 4/26/2020.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserName(String username);
}
