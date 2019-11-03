package com.myfitband.server.controller;

import com.myfitband.server.config.SessionService;
import com.myfitband.server.dao.UserRepository;
import com.myfitband.server.dto.DateObject;
import com.myfitband.server.dto.MeasurementDTO;
import com.myfitband.server.dto.SessionUser;
import com.myfitband.server.entity.*;
import com.myfitband.server.service.GPSdataService;
import com.myfitband.server.service.MeasurementService;
import com.myfitband.server.service.SettingService;
import com.myfitband.server.service.WorkoutService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RequestMapping("/workout")
@RestController
public class WorkoutController {

    @Autowired
    WorkoutService workoutService;

    @Autowired
    SessionService session;

    @Autowired
    MeasurementService measurementService;

    @Autowired
    SettingService settingService;

    @Autowired
    GPSdataService gpSdataService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/list")
    public List<Workout> getWorkouts(){
        SessionUser user = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return workoutService.getWorkoutList(user.getUserId());
    }

    @GetMapping("/{workoutId}")
    public Workout getWorkout(@PathVariable Integer workoutId){
        return workoutService.getWorkout(workoutId);
    }

    @GetMapping("/pulse/{workoutId}")
    public List<MeasurementDTO> getPulseMeasurements(@PathVariable Integer workoutId){
        return measurementService.getPulseMeasurementsForChart(workoutId);
    }

    @GetMapping("/settings")
    public Setting getNotificationSettings(){
        SessionUser user = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return settingService.getNotificationSettingsByUserId(user.getUserId());
    }

    @PostMapping(value = "/settingsNotification/save")
    public void saveNotificationSettings(@RequestBody DateObject notificationDate){
        Setting setting = new Setting();
        setting.setNotificationTime(notificationDate.getDate());
        SessionUser sessionUser = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(sessionUser.getUserId()).orElseThrow(() -> new RuntimeException("Nie odnaleziono użytkownika o id " + session.getUser().getUserId()));
        setting.setUser(user);
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
        SessionUser sessionUser = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return settingService.loadPhysicalProperties(sessionUser.getUserId());
    }

    @GetMapping("/alerts")
    public List<Alert> getAlerts(){
        SessionUser sessionUser = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return settingService.loadAlerts(sessionUser.getUserId());
    }

    @GetMapping("/weight")
    public List<MeasurementDTO> getWeightMeasurements(){
        SessionUser sessionUser = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return measurementService.getWeightMeasurementsForChart(sessionUser.getUserId());
    }
}
