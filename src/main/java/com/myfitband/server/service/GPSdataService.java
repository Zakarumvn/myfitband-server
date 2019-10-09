package com.myfitband.server.service;

import com.myfitband.server.dao.GPSDataRepository;
import com.myfitband.server.entity.GPSdata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GPSdataService {

    @Autowired
    GPSDataRepository gpsDataRepository;

    public void saveGpsDataList(List<GPSdata> dataList){
        gpsDataRepository.saveAll(dataList);
    }

    public void saveGpsMeasurement(GPSdata measurement){
        gpsDataRepository.save(measurement);
    }

    public List<GPSdata> getGPSdataForWorkout(Integer workoutId){
        return gpsDataRepository.findAllByWorkoutWorkoutIdOrderByDateAsc(workoutId);
    }
}
