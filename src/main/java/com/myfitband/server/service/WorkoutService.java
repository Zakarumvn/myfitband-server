package com.myfitband.server.service;

import com.myfitband.server.dto.Workout;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class WorkoutService {

    public List<Workout> getMockWorkouts(){
        return Arrays.asList(new Workout(1, LocalDateTime.now(), LocalDateTime.now(), "bieganie"),
                new Workout(2, LocalDateTime.now(), LocalDateTime.now(), "rower"));
    }
}
