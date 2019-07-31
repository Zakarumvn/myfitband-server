package com.myfitband.server.entity;

import javax.persistence.*;

@Entity
@Table(name = "alertTypes")
public class AlertType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer alertTypeId;

    private String description;

    public Integer getAlertTypeId() {
        return alertTypeId;
    }

    public void setAlertTypeId(Integer alertTypeId) {
        this.alertTypeId = alertTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
