package com.myfitband.server.controller;

import com.myfitband.server.config.MockSession;
import com.myfitband.server.dto.PulseDTO;
import com.myfitband.server.entity.Workout;
import com.myfitband.server.service.MeasurementService;
import com.myfitband.server.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
