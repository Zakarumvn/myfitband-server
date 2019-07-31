package com.myfitband.server.entity;

import javax.persistence.*;

@Entity
@Table(name = "configurationType")
public class ConfigurationType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer configurationTypeId;

    private String description;

    public Integer getConfigurationTypeId() {
        return configurationTypeId;
    }

    public void setConfigurationTypeId(Integer configurationTypeId) {
        this.configurationTypeId = configurationTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
