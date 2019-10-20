package com.myfitband.server.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sports")
public class Sport  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer sportId;

    private String name;

    private Integer kcalPerHour;

    private String description;

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getKcalPerHour() {
        return kcalPerHour;
    }

    public void setKcalPerHour(Integer kcalPerHour) {
        this.kcalPerHour = kcalPerHour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
