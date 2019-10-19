package com.myfitband.server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "settings")
public class Setting implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer settingId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Europe/Warsaw")
    private Date notificationTime;

    private Short active;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    @JsonManagedReference(value = "user-settings")
    private User user;

    public Setting(){}

    public Setting(Date notificationTime, Short active, User user) {
        this.notificationTime = notificationTime;
        this.active = active;
        this.user = user;
    }

    public Setting(Date notificationTime) {
        this.notificationTime = notificationTime;
    }

    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Europe/Warsaw")
    public Date getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(Date notificationTime) {
        this.notificationTime = notificationTime;
    }

    public Short getActive() {
        return active;
    }

    public void setActive(Short active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
