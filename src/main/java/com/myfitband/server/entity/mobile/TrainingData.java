package com.myfitband.server.entity.mobile;

import com.myfitband.server.entity.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TrainingData {

    private final String sportName;
    private final LoginData loginData;
    private final int distance;
    private final Long startTime;
    private final Long endTime;
    private List<SingleSecondTrainingCharacteristic> secondChars;

    public TrainingData(String sportName, LoginData loginData, int distance, Long startTime, Long endTime, ArrayList<SingleSecondTrainingCharacteristic> secondChars) {
        this.sportName = sportName;
        this.loginData = loginData;
        this.distance = distance;
        this.startTime = startTime;
        this.endTime = endTime;
        this.secondChars = secondChars;
    }

    public Workout toWorkout(Sport sport, User user) {
        Workout workout = new Workout();
        workout.setSport(sport);
        workout.setUser(user);
        workout.setStartDT(toLocalDateTime(startTime));
        workout.setStopDT(toLocalDateTime(endTime));
        return workout;
    }

    public List<Measurement> toMeasurements(Workout workout, MeasurementType type) {
        return secondChars.stream().map(s -> {
            Measurement measurement;
            measurement = new Measurement();
            measurement.setDate(toLocalDateTime(s.second));
            measurement.setValue(s.pulse.toString());
            measurement.setMeasurementType(type);
            measurement.setWorkout(workout);
            return measurement;
        }).collect(Collectors.toList());
    }

    public List<GPSdata> toGps(Workout workout) {
        return secondChars.stream().map(s -> {
            GPSdata gpSdata = new GPSdata();
            gpSdata.setGpsX(s.longitude);
            gpSdata.setGpsY(s.latitude);
            gpSdata.setSpeed(s.speed);
            gpSdata.setWorkout(workout);
            return gpSdata;
        }).collect(Collectors.toList());
    }

    private LocalDateTime toLocalDateTime(long millis) {
        return new Date(millis).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public int getDistance() {
        return distance;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public List<SingleSecondTrainingCharacteristic> getSecondChars() {
        return secondChars;
    }

    public void setSecondChars(List<SingleSecondTrainingCharacteristic> secondChars) {
        this.secondChars = secondChars;
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public String getSportName() {
        return sportName;
    }

    public static class SingleSecondTrainingCharacteristic {
        private Long second;
        private Double pulse;
        private Double latitude;
        private Double longitude;
        private Double speed;

        public SingleSecondTrainingCharacteristic() {
        }

        public SingleSecondTrainingCharacteristic(Long second, Double pulse, Double latitude, Double longitude, Double speed) {
            this.second = second;
            this.pulse = pulse;
            this.latitude = latitude;
            this.longitude = longitude;
            this.speed = speed;
        }

        public Long getSecond() {
            return second;
        }

        public void setSecond(Long second) {
            this.second = second;
        }

        public Double getPulse() {
            return pulse;
        }

        public void setPulse(Double pulse) {
            this.pulse = pulse;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public Double getSpeed() {
            return speed;
        }

        public void setSpeed(Double speed) {
            this.speed = speed;
        }
    }
}
