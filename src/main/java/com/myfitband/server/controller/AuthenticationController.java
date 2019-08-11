package com.myfitband.server.controller;

import com.myfitband.server.dto.CredentialsDTO;
import com.myfitband.server.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController("/")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public Boolean login(Principal principal){
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }

}
