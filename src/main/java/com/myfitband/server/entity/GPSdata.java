package com.myfitband.server.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "GPSdata")
public class GPSdata {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer gpsDataId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gpsData")
    private Workout workout;

    private Double gpsX;

    private Double gpsY;

    private LocalDateTime date;

    private Double speed;

    public Integer getGpsDataId() {
        return gpsDataId;
    }

    public void setGpsDataId(Integer gpsDataId) {
        this.gpsDataId = gpsDataId;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public Double getGpsX() {
        return gpsX;
    }

    public void setGpsX(Double gpsX) {
        this.gpsX = gpsX;
    }

    public Double getGpsY() {
        return gpsY;
    }

    public void setGpsY(Double gpsY) {
        this.gpsY = gpsY;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }


}
