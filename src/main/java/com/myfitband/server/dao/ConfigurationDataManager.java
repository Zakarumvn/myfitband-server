package com.myfitband.server.dao;


import com.myfitband.server.config.DataManager;
import com.myfitband.server.entity.AlertType;
import com.myfitband.server.entity.MeasurementType;
import com.myfitband.server.entity.Sport;
import com.myfitband.server.entity.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConfigurationDataManager {

    @Autowired private DataManager dm;

    public void saveAlertType(AlertType alertType){
        dm.saveOrUpdate(alertType);
    }

    public void saveMeasurementType(MeasurementType measurementType){
        dm.saveOrUpdate(measurementType);
    }

    public void saveSport(Sport sport){
        dm.saveOrUpdate(sport);
    }

    public void saveWorkout(Workout workout){
        dm.saveOrUpdate(workout);
    }

}
