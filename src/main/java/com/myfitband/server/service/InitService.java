package com.myfitband.server.service;

import com.myfitband.server.dao.*;
import com.myfitband.server.entity.*;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    MeasurementTypeRepository measurementTypeRepository;

    @PostConstruct
    public void initData(){

        if(sportRepository.findAll().size() == 0){
            User user = new User();
            user.setFirstName("Kasia");
            user.setLastName("Testowa");
            user.setEmail("testowa.kasia@gmail.com");
            user.setLogin("testowa");
            user.setBirthDate(LocalDateTime.now().minusYears(20));
            user.setPassword("kasia");
            userRepository.save(user);

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
                w1.setUser(user);
                w1.setStopDT(LocalDateTime.now());
                workoutRepository.save(w1);

                Workout w2 = new Workout();
                w2.setSport(s2);
                w2.setUser(user);
                w2.setStartDT(LocalDateTime.now());
                w2.setStopDT(LocalDateTime.now());
                workoutRepository.save(w2);

                MeasurementType measurementType = new MeasurementType();
                measurementType.setDescription("pomiar pulsu");
                measurementTypeRepository.save(measurementType);


                Measurement m1 = new Measurement();
                m1.setMeasurementType(measurementType);
                m1.setDate(LocalDateTime.now());
                m1.setValue("42");
                m1.setWorkout(w1);
                measurementRepository.save(m1);

                Measurement m2 = new Measurement();
                m2.setMeasurementType(measurementType);
                m2.setDate(LocalDateTime.now().plusMinutes(1));
                m2.setValue("46");
                m2.setWorkout(w1);
                measurementRepository.save(m2);

                Measurement m3 = new Measurement();
                m3.setMeasurementType(measurementType);
                m3.setDate(LocalDateTime.now().plusMinutes(2));
                m3.setValue("51");
                m3.setWorkout(w1);
                measurementRepository.save(m3);

                Measurement m4 = new Measurement();
                m4.setMeasurementType(measurementType);
                m4.setDate(LocalDateTime.now().plusMinutes(3));
                m4.setValue("58");
                m4.setWorkout(w1);
                measurementRepository.save(m4);

                Measurement m5 = new Measurement();
                m5.setMeasurementType(measurementType);
                m5.setDate(LocalDateTime.now().plusMinutes(4));
                m5.setValue("63");
                m5.setWorkout(w1);
                measurementRepository.save(m5);
            }


        }


    }

}
