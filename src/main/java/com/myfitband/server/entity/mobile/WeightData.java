package com.myfitband.server.entity.mobile;

import com.myfitband.server.entity.Measurement;
import com.myfitband.server.entity.MeasurementType;
import com.myfitband.server.entity.User;

import java.time.LocalDateTime;

public class WeightData {
    private final Double weight;
    private final LoginData loginData;

    public WeightData(Double weight, LoginData loginData) {
        this.weight = weight;
        this.loginData = loginData;
    }

    public Double getWeight() {
        return weight;
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public Measurement toMeasurement(MeasurementType type, User user) {
        return new Measurement(
                type,
                weight.toString(),
                LocalDateTime.now(),
                null,
                user
        );
    }
}
