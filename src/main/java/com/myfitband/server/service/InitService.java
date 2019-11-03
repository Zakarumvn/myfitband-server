package com.myfitband.server.service;

import com.myfitband.server.dao.*;
import com.myfitband.server.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

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

    @Autowired
    PhysicalPropertiesRepository physicalPropertiesRepository;

    @Autowired
    AlertTypeRepository alertTypeRepository;

    @Autowired
    AlertRepository alertRepository;

    @PostConstruct
    public void initData(){

        if(sportRepository.findAll().size() == 0){
            User user = new User();
            user.setFirstName("Kasia");
            user.setLastName("Testowa");
            user.setEmail("testowa.kasia@gmail.com");
            user.setLogin("testowa");
            user.setBirthDate(LocalDateTime.now().minusYears(20));
            user.setPassword(new BCryptPasswordEncoder().encode("kasia"));
            userRepository.save(user);

            PhysicalProperties physicalProperties = new PhysicalProperties();
            physicalProperties.setUser(user);
            physicalProperties.setRelaxPulse(70);
            physicalProperties.setStressPulse(190);
            physicalProperties.setHeight(160);
            physicalProperties.setBoneMass(2.1);
            physicalProperties.setFatMass(21.0);
            physicalProperties.setMuscleMass(60.0);
            physicalProperties.setWater(50.0);
            physicalPropertiesRepository.save(physicalProperties);

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

                MeasurementType measurementType2 = new MeasurementType();
                measurementType2.setDescription("pomiar wagi");
                measurementTypeRepository.save(measurementType2);

                Random generator = new Random();

                for (int i = 0; i < 2000; i++) {
                    LocalDateTime ldt = LocalDateTime.now();
                    ldt.plusSeconds(5);
                    measurementRepository.save(new Measurement(
                            measurementType,
                            String.valueOf(generator.nextInt(101)+ 90),
                            ldt,
                            w1,
                            null
                    ));

                }

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

                //map 2


                GPSdata g21 = new GPSdata();
                g21.setDate(LocalDateTime.now());
                g21.setWorkout(w2);
                //longitude
                g21.setGpsX(22.549046);
                //latitude
                g21.setGpsY(51.235723);

                GPSdata g22 = new GPSdata();
                g22.setDate(LocalDateTime.now().plusSeconds(30));
                g22.setWorkout(w2);
                //longitude
                g22.setGpsX(22.548440);
                //latitude
                g22.setGpsY(51.236049);


                GPSdata g23 = new GPSdata();
                g23.setDate(LocalDateTime.now().plusSeconds(50));
                g23.setWorkout(w2);
                //longitude
                g23.setGpsX(22.547624);
                //latitude
                g23.setGpsY(51.236489);

                GPSdata g24 = new GPSdata();
                g24.setDate(LocalDateTime.now().plusSeconds(70));
                g24.setWorkout(w2);
                //longitude
                g24.setGpsX(22.545861);
                //latitude
                g24.setGpsY(51.236917);

                GPSdata g25 = new GPSdata();
                g25.setDate(LocalDateTime.now().plusSeconds(90));
                g25.setWorkout(w2);
                //longitude
                g25.setGpsX(22.543836);
                //latitude
                g25.setGpsY(51.237307);

                GPSdata g26 = new GPSdata();
                g26.setDate(LocalDateTime.now().plusSeconds(110));
                g26.setWorkout(w2);
                //longitude
                g26.setGpsX(22.543510);
                //latitude
                g26.setGpsY(51.239161);

                gpsDataRepository.save(g21);
                gpsDataRepository.save(g22);
                gpsDataRepository.save(g23);
                gpsDataRepository.save(g24);
                gpsDataRepository.save(g25);
                gpsDataRepository.save(g26);

                AlertType alertType1 = new AlertType();
                alertType1.setDescription("przemęczenie");
                alertTypeRepository.save(alertType1);

                AlertType alertType2 = new AlertType();
                alertType2.setDescription("brak aktywności");
                alertTypeRepository.save(alertType2);


                Alert alert = new Alert();
                alert.setUser(user);
                alert.setAlertType(alertType1);
                alert.setDate(LocalDateTime.now());
                alert.setDescription("Uwaga! Podczas treningu Twój puls osiagnął niebezpieczny poziom: 120 uderzeń na sekundę.");
                alertRepository.save(alert);

                Alert alert2 = new Alert();
                alert2.setUser(user);
                alert2.setAlertType(alertType2);
                alert2.setDate(LocalDateTime.now());
                alert2.setDescription("Uwaga! System wykrył brak aktywności podczas treningu.");
                alertRepository.save(alert2);


                Double x = new Double("55.0");
                LocalDateTime weightMeasuremntDate = LocalDateTime.now().minusDays(30);
                for (int i = 0; i < 25; i++) {
                    LocalDateTime date = weightMeasuremntDate.plusDays(i);
                    measurementRepository.save(new Measurement(
                            measurementType2,
                            String.valueOf(generator.nextDouble()*10+ 45),
                            date,
                            null,
                            user
                    ));

                }

            }
        }

    }

}
