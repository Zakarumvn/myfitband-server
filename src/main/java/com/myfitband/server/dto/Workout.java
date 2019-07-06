package com.myfitband.server.dto;


import java.time.LocalDateTime;

public class Workout {

    private Integer workoutId;

    private LocalDateTime startDT;

    private LocalDateTime stopDT;

    private String sport;

    public Workout(Integer workoutId, LocalDateTime startDT, LocalDateTime stopDT, String sport) {
        this.workoutId = workoutId;
        this.startDT = startDT;
        this.stopDT = stopDT;
        this.sport = sport;
    }

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

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}
