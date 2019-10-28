package com.myfitband.server.service;

import com.myfitband.server.dto.JwtRequest;
import com.myfitband.server.dto.SessionUser;
import com.myfitband.server.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return new SessionUser(user.getUserId(), user.getLogin(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public User save(JwtRequest request) {
        User user = new User();

        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        user.setLogin(request.getUsername());

        return userService.saveUser(user);
    }
}
