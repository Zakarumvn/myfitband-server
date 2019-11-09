package com.myfitband.server.entity.mobile;

import java.time.LocalDateTime;

import com.myfitband.server.entity.Alert;
import com.myfitband.server.entity.AlertType;
import com.myfitband.server.entity.User;

public class NewAlert {

    private LoginData loginData;

    public NewAlert(){

    }

    public NewAlert(LoginData loginData) {
        this.loginData = loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public Alert toAlert(AlertType type, User user, String description) {
        Alert alert = new Alert();
        alert.setAlertType(type);
        alert.setDescription(description);
        alert.setDate(LocalDateTime.now());
        alert.setUser(user);
        return alert;
    }
}
