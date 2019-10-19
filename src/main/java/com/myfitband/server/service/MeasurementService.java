package com.myfitband.server.service;

import com.myfitband.server.dao.MeasurementRepository;
import com.myfitband.server.dto.MeasurementDTO;
import com.myfitband.server.entity.Measurement;
import com.myfitband.server.service.utils.MeasurementDtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public List<MeasurementDTO> getPulseMeasurementsForChart(Integer workoutId){
        List<Measurement> pulseMeasurements = measurementRepository.findAllByWorkoutWorkoutId(workoutId);
        return pulseMeasurements
                .stream()
                .map(m -> new MeasurementDTO(MeasurementDtoUtils.getHourAsString(m.getDate()),
                        m.getValue()))
                .collect(Collectors.toList());
    }

    public List<MeasurementDTO> getWeightMeasurementsForChart(Integer userId){
        List<Measurement> weightMeasurements = measurementRepository.findAllByUserUserIdAndUserNotNull(userId);
        return weightMeasurements
                .stream()
                .map(m ->
                        new MeasurementDTO(MeasurementDtoUtils.getDateAsString(m.getDate()),
                                MeasurementDtoUtils.getWeightString(m.getValue())))
                .collect(Collectors.toList());
    }
}
