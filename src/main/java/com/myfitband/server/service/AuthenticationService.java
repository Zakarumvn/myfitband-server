package com.myfitband.server.service;

import com.myfitband.server.dao.UserRepository;
import com.myfitband.server.dto.CredentialsDTO;
import com.myfitband.server.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    public User authenticate(CredentialsDTO credentialsDTO){
        if(credentialsDTO == null) throw new RuntimeException("Nie podano loginu/hasła!");
        User u = userRepository.findByLogin(credentialsDTO.getUsername());
        if(credentialsDTO.getPassword().equals(u.getPassword())){
            return u;
        }

        return null;
    }
}
