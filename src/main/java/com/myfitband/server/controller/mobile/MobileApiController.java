package com.myfitband.server.controller.mobile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myfitband.server.dao.*;
import com.myfitband.server.entity.*;
import com.myfitband.server.entity.mobile.*;
import com.myfitband.server.service.SportService;
import com.myfitband.server.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/mobile")
public class MobileApiController {

    private final UserService userService;

    private final SportService sportService;

    private final MeasurementTypeRepository measurementTypeRepository;

    private final MeasurementRepository measurementRepository;

    private final WorkoutRepository workoutRepository;

    private final GPSDataRepository gpsDataRepository;

    private final DeviceRepository deviceRepository;

    private final AlertTypeRepository alertTypeRepository;

    private final AlertRepository alertRepository;

    private final PhysicalPropertiesRepository physicalPropertiesRepository;

    public MobileApiController(UserService userService, SportService sportService, MeasurementTypeRepository measurementTypeRepository,
                               MeasurementRepository measurementRepository, WorkoutRepository workoutRepository, GPSDataRepository gpsDataRepository,
                               DeviceRepository deviceRepository, AlertTypeRepository alertTypeRepository,
                               AlertRepository alertRepository, PhysicalPropertiesRepository physicalPropertiesRepository) {
        this.userService = userService;
        this.sportService = sportService;
        this.measurementTypeRepository = measurementTypeRepository;
        this.measurementRepository = measurementRepository;
        this.workoutRepository = workoutRepository;
        this.gpsDataRepository = gpsDataRepository;
        this.deviceRepository = deviceRepository;
        this.alertTypeRepository = alertTypeRepository;
        this.alertRepository = alertRepository;
        this.physicalPropertiesRepository = physicalPropertiesRepository;
    }

    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public PostResponse registerUser(@RequestBody UserData userData) {
        User user = userData.mapToUser();
        PhysicalProperties physicalProperties = userData.mapToPhysicalProperties(user);
        if (userService.userCanBeCreated(user)) {
            userService.createNewUser(user);
            physicalPropertiesRepository.save(physicalProperties);
            return PostResponse.ok();

        }
        return PostResponse.error("User with these credentials already exists");
    }

    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public PostResponse login(@RequestBody LoginData loginData) throws JsonProcessingException {
        Optional<User> user = userService.getDataOfUser(loginData);
        if (!user.isPresent()) {
            return PostResponse.error("There is no user with such credentials");
        }
        PhysicalProperties physicalProperties = physicalPropertiesRepository.findByUserUserId(user.get().getUserId());
        return PostResponse.ok(new ObjectMapper().writeValueAsString(new PulseValues(physicalProperties.getRelaxPulse(), physicalProperties.getStressPulse())));
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
        // pobierz dane uzytkownika o danych analogicznych do przeslanych w body
        User user = userService.getDataOfUser(tokenData.getLoginData())
                // wyjatek w przypadku braku odpowiadajacego uzytkownika
                .orElseThrow(() -> new IllegalArgumentException("Cannot create new weight for not existing user"));
        // zapis tokenu w bazie danych
        deviceRepository.save(tokenData.toDevice(user));
        // zwroc: udalo sie!
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

    @PostMapping(value = "/newPulse", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public PostResponse newPulse(@RequestBody PulseData pulseData) {
        User user = userService.getDataOfUser(pulseData.getLoginData())
                .orElseThrow(() -> new IllegalArgumentException("Cannot create new weight for not existing user"));
        PhysicalProperties physicalProperties = pulseData.mapToPhysicalProperties(user);
        physicalPropertiesRepository.findAll().stream().filter(p -> p.getUser().getLogin().equals(user.getLogin()))
                .forEach(physicalPropertiesRepository::delete);
        physicalPropertiesRepository.save(physicalProperties);
        return PostResponse.ok();
    }

    @PostMapping(value = "/newPrzemeczenie", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public PostResponse newPrzemeczenie(@RequestBody NewAlert newAlert) {
        User user = userService.getDataOfUser(newAlert.getLoginData())
                .orElseThrow(() -> new IllegalArgumentException("Cannot create new alert"));
        Alert alert = newAlert.toAlert(getPrzemeczenieAlertType(), user, "Uwaga! Podczas treningu Twój puls osiagnął niebezpieczny poziom!");
        alertRepository.save(alert);
        return PostResponse.ok();
    }

    @PostMapping(value = "/newGpsAlert", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public PostResponse newGpsAlert(@RequestBody NewAlert newAlert) {
        User user = userService.getDataOfUser(newAlert.getLoginData())
                .orElseThrow(() -> new IllegalArgumentException("Cannot create new alert"));
        Alert alert = newAlert.toAlert(getGpsAlertType(), user, "Uwaga! System wykrył brak aktywności podczas treningu.");
        alertRepository.save(alert);
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

    private AlertType getPrzemeczenieAlertType() {
        return alertTypeRepository.findAll().stream()
                .filter(at -> at.getDescription().equalsIgnoreCase("przemęczenie")).findFirst()
                .orElseThrow(() -> new IllegalStateException("cannot find alert type"));
    }

    private AlertType getGpsAlertType() {
        return alertTypeRepository.findAll().stream()
                .filter(at -> at.getDescription().equalsIgnoreCase("brak aktywności")).findFirst()
                .orElseThrow(() -> new IllegalStateException("cannot find alert type"));
    }
}
