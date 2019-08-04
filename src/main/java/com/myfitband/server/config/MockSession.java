package com.myfitband.server.config;

import com.myfitband.server.dao.UserRepository;
import com.myfitband.server.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MockSession {

    private User user;

    @Autowired
    private UserRepository repository;

    public User getUser(){
        if(user == null || user.getLogin() == null){
            user = repository.findById(1).orElse(new User(1));
        }
        return user;
    }
}
