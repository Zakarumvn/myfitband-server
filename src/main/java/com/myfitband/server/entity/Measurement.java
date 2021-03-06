package com.myfitband.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer measurementId;

    @ManyToOne
    @JoinColumn(name = "measurementTypeId")
    private MeasurementType measurementType;

    private String value;

    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout")
    @JsonBackReference("workout-measurements")
    private Workout workout;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    @JsonBackReference("user-measurements")
    private User user;

    public Measurement(){}

    public Measurement(MeasurementType measurementType, String value, LocalDateTime date, Workout workout, User user) {
        this.measurementType = measurementType;
        this.value = value;
        this.date = date;
        this.workout = workout;
        this.user = user;
    }

    public Integer getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(Integer measurementId) {
        this.measurementId = measurementId;
    }

    public MeasurementType getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
