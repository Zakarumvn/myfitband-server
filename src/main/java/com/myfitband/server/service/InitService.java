package com.myfitband.server.service;

import com.myfitband.server.dao.SportRepository;
import com.myfitband.server.dao.WorkoutRepository;
import com.myfitband.server.entity.Sport;
import com.myfitband.server.entity.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
public class InitService {

    @Autowired
    SportRepository sportRepository;

    @Autowired
    WorkoutRepository workoutRepository;

    @PostConstruct
    public void initData(){

        if(sportRepository.findAll().size() == 0){
            Sport s1 = new Sport();
            s1.setName("bieganie");
            s1.setKcalPerHour(450);
            sportRepository.save(s1);

            Sport s2 = new Sport();
            s2.setName("jazda na rowerze");
            s2.setKcalPerHour(250);
            sportRepository.save(s2);

            if(workoutRepository.findAll().size() == 0 ){
                Workout w1 = new Workout();
                w1.setSport(s1);
                w1.setStartDT(LocalDateTime.now());
                w1.setStopDT(LocalDateTime.now());
                workoutRepository.save(w1);

                Workout w2 = new Workout();
                w2.setSport(s2);
                w2.setStartDT(LocalDateTime.now());
                w2.setStopDT(LocalDateTime.now());
                workoutRepository.save(w2);
            }


        }


    }

}
