package com.myfitband.server.controller;

import com.myfitband.server.config.MockSession;
import com.myfitband.server.dto.DateObject;
import com.myfitband.server.dto.PulseDTO;
import com.myfitband.server.entity.*;
import com.myfitband.server.service.GPSdataService;
import com.myfitband.server.service.MeasurementService;
import com.myfitband.server.service.SettingService;
import com.myfitband.server.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        setting.setActive(Short.valueOf("1"));
        setting.setSettingId(notificationDate.getSettingId());
        settingService.saveNotificationSettings(setting);
    }

    @GetMapping(value = "/map/{workoutId}")
    public List<GPSdata> getRouteForWorkout(@PathVariable Integer workoutId){
        return gpSdataService.getGPSdataForWorkout(workoutId);
    }

    @GetMapping("/physicalProperties")
    public PhysicalProperties getPhysicalProperties(){
        return settingService.loadPhysicalProperties(session.getUser().getUserId());
    }

    @GetMapping("/alerts")
    public List<Alert> getAlerts(){
        return settingService.loadAlerts(session.getUser().getUserId());
    }

}
