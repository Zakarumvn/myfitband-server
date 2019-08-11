package com.myfitband.server.service;

import com.myfitband.server.dao.UserRepository;
import com.myfitband.server.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
       org.springframework.security.core.userdetails.User.UserBuilder builder = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder();
       User user = userRepository.findByLogin(login);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return builder
                .username(user.getLogin())
                .password(user.getPassword())
                .roles("USER")
                .build();

//        return new org.springframework.security.core.userdetails.User(((User) user).getLogin(), user.getPassword(), getAuthority());
    }

    private List getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

}
