package com.myfitband.server.controller.mobile;

import com.myfitband.server.UserService;
import com.myfitband.server.entity.User;
import com.myfitband.server.entity.mobile.LoginData;
import com.myfitband.server.entity.mobile.UserData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mobile")
public class MobileApiController {

    private final UserService userService;

    public MobileApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public PostResponse registerUser(@RequestBody UserData userData) {
        User user = userData.mapToUser();
        if (userService.userCanBeCreated(user)) {
            userService.createNewUser(user);
            return PostResponse.ok();

        }
        return PostResponse.error("User with these credentials already exists");
    }

    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public PostResponse login(@RequestBody LoginData loginData) {
        return userService.userExists(loginData) ?
                PostResponse.ok() :
                PostResponse.error("There is no user with such credentials");
    }

    @PostMapping(value = "/removeAccount", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public PostResponse removeAccount(@RequestBody LoginData loginData) {
        if (userService.userExists(loginData)) {
            userService.removeUser(loginData);
        }
        return PostResponse.ok();
    }
}
