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

    @Autowired
    GPSDataRepository gpsDataRepository;

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

                GPSdata g1 = new GPSdata();
                g1.setDate(LocalDateTime.now());
                g1.setWorkout(w1);
                //longitude
                g1.setGpsX(22.5276146);
                //latitude
                g1.setGpsY(51.2708819);

                GPSdata g2 = new GPSdata();
                g2.setDate(LocalDateTime.now().plusSeconds(30));
                g2.setWorkout(w1);
                //longitude
                g2.setGpsX(22.528085);
                //latitude
                g2.setGpsY(51.270903);

                GPSdata g3 = new GPSdata();
                g3.setDate(LocalDateTime.now().plusSeconds(50));
                g3.setWorkout(w1);
                //longitude
                g3.setGpsX(22.527542);
                //latitude
                g3.setGpsY(51.270407);

                GPSdata g4 = new GPSdata();
                g4.setDate(LocalDateTime.now().plusSeconds(70));
                g4.setWorkout(w1);
                //longitude
                g4.setGpsX(22.528068);
                //latitude
                g4.setGpsY(51.270642);

                GPSdata g5 = new GPSdata();
                g5.setDate(LocalDateTime.now().plusSeconds(90));
                g5.setWorkout(w1);
                //longitude
                g5.setGpsX(22.526576);
                //latitude
                g5.setGpsY(51.270446);

                GPSdata g6 = new GPSdata();
                g6.setDate(LocalDateTime.now().plusSeconds(110));
                g6.setWorkout(w1);
                //longitude
                g6.setGpsX(22.526557);
                //latitude
                g6.setGpsY(51.269930);

                GPSdata g7 = new GPSdata();
                g7.setDate(LocalDateTime.now().plusSeconds(130));
                g7.setWorkout(w1);
                //longitude
                g7.setGpsX(22.527206);
                //latitude
                g7.setGpsY(51.269648);

                gpsDataRepository.save(g1);
                gpsDataRepository.save(g2);
                gpsDataRepository.save(g3);
                gpsDataRepository.save(g4);
                gpsDataRepository.save(g5);
                gpsDataRepository.save(g6);
                gpsDataRepository.save(g7);
            }


        }


    }

}
