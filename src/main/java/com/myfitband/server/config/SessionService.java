package com.myfitband.server.config;

import com.myfitband.server.dao.UserRepository;
import com.myfitband.server.dto.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class SessionService {

    @Autowired
    UserRepository userRepository;

    private SessionUser user;

    public SessionUser getUser() {
        return user;
    }

    public void setUser(SessionUser user) {
        this.user = user;
    }

    public String getFirstname() {
        return user.getFirstName();
    }

    public String getLastname() {
        return user.getLastName();
    }

    public Integer getUserId(){
        return user.getUserId();
    }

}