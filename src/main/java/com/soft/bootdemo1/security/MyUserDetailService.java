package com.soft.bootdemo1.security;

import com.soft.bootdemo1.dao.UserRepository;
import com.soft.bootdemo1.model.MyUserDetails;
import com.soft.bootdemo1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByUserName(username);

        System.out.println(optionalUser.get());
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Not Found " + username));

        return optionalUser.map(MyUserDetails::new).get();
    }
}
