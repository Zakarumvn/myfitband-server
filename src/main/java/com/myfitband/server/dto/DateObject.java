package com.myfitband.server.dto;

import java.io.Serializable;
import java.util.Date;

public class DateObject implements Serializable {

    private Date date;

    private Integer settingId;

    public DateObject(Date notificationTime) {
        this.date = notificationTime;
    }

    public DateObject() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }
}
