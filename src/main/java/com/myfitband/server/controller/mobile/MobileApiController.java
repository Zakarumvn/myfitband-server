package com.myfitband.server.controller.mobile;

import com.myfitband.server.dao.*;
import com.myfitband.server.entity.*;
import com.myfitband.server.entity.mobile.*;
import com.myfitband.server.service.SportService;
import com.myfitband.server.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mobile")
public class MobileApiController {

    private final UserService userService;
    private final SportService sportService;
    private final MeasurementTypeRepository measurementTypeRepository;
    private final MeasurementRepository measurementRepository;
    private final WorkoutRepository workoutRepository;
    private final GPSDataRepository gpsDataRepository;
    private final DeviceRepository deviceRepository;

    public MobileApiController(UserService userService, SportService sportService, MeasurementTypeRepository measurementTypeRepository, MeasurementRepository measurementRepository, WorkoutRepository workoutRepository, GPSDataRepository gpsDataRepository, DeviceRepository deviceRepository) {
        this.userService = userService;
        this.sportService = sportService;
        this.measurementTypeRepository = measurementTypeRepository;
        this.measurementRepository = measurementRepository;
        this.workoutRepository = workoutRepository;
        this.gpsDataRepository = gpsDataRepository;
        this.deviceRepository = deviceRepository;
    }

    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public PostResponse registerUser(@RequestBody UserData userData) {
        User user = userData.mapToUser();
        if (userService.userCanBeCreated(user)) {
            userService.createNewUser(user);
            return PostResponse.ok();

        }
        return PostResponse.error("User with these credentials already exists");
    }

    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public PostResponse login(@RequestBody LoginData loginData) {
        return userService.userExists(loginData) ?
                PostResponse.ok() :
                PostResponse.error("There is no user with such credentials");
    }

    @PostMapping(value = "/removeAccount", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public PostResponse removeAccount(@RequestBody LoginData loginData) {
        if (userService.userExists(loginData)) {
            userService.removeUser(loginData);
        }
        return PostResponse.ok();
    }

    @PostMapping(value = "/postTraining", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public PostResponse postTraining(@RequestBody TrainingData trainingData) {
        User user = userService.getDataOfUser(trainingData.getLoginData())
                .orElseThrow(() -> new IllegalArgumentException("Cannot create workout for not existing user"));
        Sport sport = sportService.findSportByName(trainingData.getSportName())
                .orElseThrow(() -> new IllegalArgumentException("Cannot create workout for not existing sport"));
        MeasurementType pulseMeasurementType = getPulseMeasurementType();

        Workout workout = trainingData.toWorkout(sport, user);
        workoutRepository.save(workout);

        trainingData.toMeasurements(workout, pulseMeasurementType).forEach(measurementRepository::save);
        trainingData.toGps(workout).forEach(gpsDataRepository::save);
        return PostResponse.ok();
    }

    @PostMapping(value = "/newToken", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public PostResponse newToken(@RequestBody TokenData tokenData) {
        User user = userService.getDataOfUser(tokenData.getLoginData())
                .orElseThrow(() -> new IllegalArgumentException("Cannot create new weight for not existing user"));
        deviceRepository.save(tokenData.toDevice(user));
        return PostResponse.ok();
    }

    @PostMapping(value = "/newWeight", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public PostResponse newWeight(@RequestBody WeightData weightData) {
        User user = userService.getDataOfUser(weightData.getLoginData())
                .orElseThrow(() -> new IllegalArgumentException("Cannot create new weight for not existing user"));
        MeasurementType measurementType = getWeightMeasurementType();
        Measurement measurement = weightData.toMeasurement(measurementType, user);
        measurementRepository.save(measurement);
        return PostResponse.ok();
    }

    private MeasurementType getPulseMeasurementType() {
        return measurementTypeRepository.findAll().stream()
                .filter(s -> s.getDescription().equalsIgnoreCase("pomiar pulsu")).findFirst()
                .orElseThrow(() -> new IllegalStateException("Cannot create workout, as database is not ready"));
    }

    private MeasurementType getWeightMeasurementType() {
        return measurementTypeRepository.findAll().stream()
                .filter(s -> s.getDescription().equalsIgnoreCase("pomiar wagi")).findFirst()
                .orElseThrow(() -> new IllegalStateException("Cannot create workout, as database is not ready"));
    }
}
