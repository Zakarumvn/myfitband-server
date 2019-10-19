package com.myfitband.server.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "workouts")
public class Workout  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer workoutId;

    private LocalDateTime startDT;

    private LocalDateTime stopDT;

    @OneToMany(mappedBy = "workout", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "workout-measurements")
    private Set<Measurement> measurements;

    @JsonManagedReference(value = "workout-gpsData")
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="workout")
    private Set<GPSdata> gpsData;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sport")
    private Sport sport;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    @JsonManagedReference(value = "user-workout")
    private User user;

    public Integer getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Integer workoutId) {
        this.workoutId = workoutId;
    }

    public LocalDateTime getStartDT() {
        return startDT;
    }

    public void setStartDT(LocalDateTime startDT) {
        this.startDT = startDT;
    }

    public LocalDateTime getStopDT() {
        return stopDT;
    }

    public void setStopDT(LocalDateTime stopDT) {
        this.stopDT = stopDT;
    }

    public Set getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Set measurements) {
        this.measurements = measurements;
    }

    public Set getGpsData() {
        return gpsData;
    }

    public void setGpsData(Set gpsData) {
        this.gpsData = gpsData;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
