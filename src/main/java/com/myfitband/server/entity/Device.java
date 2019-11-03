package com.myfitband.server.entity;

import javax.persistence.*;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer deviceId;

    private String fireBaseToken;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    private User user;

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getFireBaseToken() {
        return fireBaseToken;
    }

    public void setFireBaseToken(String fireBaseToken) {
        this.fireBaseToken = fireBaseToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
