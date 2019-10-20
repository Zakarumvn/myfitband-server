package com.myfitband.server.service;

import com.myfitband.server.dao.MeasurementRepository;
import com.myfitband.server.dto.PulseDTO;
import com.myfitband.server.entity.Measurement;
import com.myfitband.server.service.utils.PulseChartUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public List<PulseDTO> getPulseMeasurementsForChart(Integer workoutId){
        List<Measurement> pulseMeasurements = measurementRepository.findAllByWorkoutWorkoutId(workoutId);
        return pulseMeasurements.stream().map(m -> new PulseDTO(PulseChartUtils.getHourAsString(m.getDate()), m.getValue())).collect(Collectors.toList());
    }
}
