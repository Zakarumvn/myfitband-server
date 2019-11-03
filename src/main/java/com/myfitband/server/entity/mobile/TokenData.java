package com.myfitband.server.entity.mobile;

import com.myfitband.server.entity.Device;
import com.myfitband.server.entity.User;

public class TokenData {
    private final String token;
    private final LoginData loginData;

    public TokenData(String token, LoginData loginData) {
        this.token = token;
        this.loginData = loginData;
    }

    public String getToken() {
        return token;
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public Device toDevice(User user) {
        Device device = new Device();
        device.setFireBaseToken(token);
        device.setUser(user);
        return device;
    }
}
