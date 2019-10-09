package com.myfitband.server.controller;

import com.myfitband.server.dao.UserRepository;
import com.myfitband.server.entity.mobile.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mobile")
public class MobileApiController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/register", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public boolean registerUser(@RequestBody UserData userData) {
        userRepository.save(userData.mapToUser());
        return true;
    }

    @GetMapping("/kupa")
    public void kupakupy() {
        System.out.println("kupa");
    }

}
