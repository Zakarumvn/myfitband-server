package com.myfitband.server.service;

import com.myfitband.server.dao.WorkoutRepository;
import com.myfitband.server.entity.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class WorkoutService {

    @Autowired
    WorkoutRepository workoutRepository;

    public List<Workout> getWorkoutList(){
        return workoutRepository.findAll();
    }

    public Workout getWorkout(Integer workoutId){
        return workoutRepository.findById(workoutId).orElse(null);
    }
}
