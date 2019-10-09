package com.myfitband.server.entity;

import java.io.Serializable;
import java.util.Date;

public class DateObject implements Serializable {

    private Date date;

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
}
