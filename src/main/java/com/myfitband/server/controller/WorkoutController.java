package com.myfitband.server.controller;

import com.myfitband.server.config.MockSession;
import com.myfitband.server.dto.PulseDTO;
import com.myfitband.server.entity.DateObject;
import com.myfitband.server.entity.GPSdata;
import com.myfitband.server.entity.Setting;
import com.myfitband.server.entity.Workout;
import com.myfitband.server.service.GPSdataService;
import com.myfitband.server.service.MeasurementService;
import com.myfitband.server.service.SettingService;
import com.myfitband.server.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/workout")
@RestController
public class WorkoutController {

    @Autowired
    WorkoutService workoutService;

    @Autowired
    MockSession session;

    @Autowired
    MeasurementService measurementService;

    @Autowired
    SettingService settingService;

    @Autowired
    GPSdataService gpSdataService;

    @GetMapping("/list")
    public List<Workout> getWorkouts(){
        return workoutService.getWorkoutList(session.getUser().getUserId());
    }

    @GetMapping("/{workoutId}")
    public Workout getWorkout(@PathVariable Integer workoutId){
        return workoutService.getWorkout(workoutId);
    }

    @GetMapping("/pulse/{workoutId}")
    public List<PulseDTO> getPulseMeasurements(@PathVariable Integer workoutId){
        return measurementService.getPulseMeasurementsForChart(workoutId);
    }

    @GetMapping("/settings")
    public Setting getNotificationSettings(){
        return settingService.getNotificationSettingsByUserId(session.getUser().getUserId());
    }

    @PostMapping(value = "/settingsNotification/save")
    public void saveNotificationSettings(@RequestBody DateObject notificationDate){
        Setting setting = new Setting();
        setting.setNotificationTime(notificationDate.getDate());
        setting.setUser(session.getUser());
        settingService.saveNotificationSettings(setting);
    }

    @PostMapping(value = "/settings/save")
    public void saveSettings(Setting setting){
        if(setting.getNotificationTime() != null){
            setting.setUser(session.getUser());
            setting.setActive(Short.valueOf("1"));
            settingService.saveNotificationSettings(setting);
        }
    }

    @GetMapping(value = "/map/{workoutId}")
    public List<GPSdata> getRouteForWorkout(@PathVariable Integer workoutId){
        return gpSdataService.getGPSdataForWorkout(workoutId);
    }

}
