package com.myfitband.server.controller;

import com.myfitband.server.dto.Workout;
import com.myfitband.server.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/workout")
@RestController
public class WorkoutController {

    @Autowired WorkoutService workoutService;

    @GetMapping("/list")
    public List<Workout> getWorkouts(){
        return workoutService.getMockWorkouts();
    }
}
