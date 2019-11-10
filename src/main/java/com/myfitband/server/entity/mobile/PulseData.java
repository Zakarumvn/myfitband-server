package com.myfitband.server.entity.mobile;

import com.myfitband.server.entity.PhysicalProperties;
import com.myfitband.server.entity.User;

public class PulseData {
    private Integer pulseMin;
    private Integer pulseMax;
    private LoginData loginData;


    public PulseData(Integer pulseMin, Integer pulseMax, LoginData loginData) {
        this.pulseMin = pulseMin;
        this.pulseMax = pulseMax;
        this.loginData = loginData;
    }

    public PulseData() {
    }

    public Integer getPulseMin() {
        return pulseMin;
    }

    public void setPulseMin(Integer pulseMin) {
        this.pulseMin = pulseMin;
    }

    public Integer getPulseMax() {
        return pulseMax;
    }

    public void setPulseMax(Integer pulseMax) {
        this.pulseMax = pulseMax;
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

    public PhysicalProperties mapToPhysicalProperties(User user) {
        PhysicalProperties physicalProperties = new PhysicalProperties();
        physicalProperties.setUser(user);
        physicalProperties.setRelaxPulse(pulseMin);
        physicalProperties.setStressPulse(pulseMax);
        return physicalProperties;
    }
}
