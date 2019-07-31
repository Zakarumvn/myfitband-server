package com.myfitband.server.entity;

import javax.persistence.*;

@Entity
@Table(name = "measurementTypes")
public class MeasurementType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer measurementType;

    private String description;

    public Integer getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(Integer measurementType) {
        this.measurementType = measurementType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
