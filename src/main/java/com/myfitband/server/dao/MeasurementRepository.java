package com.myfitband.server.dao;

import com.myfitband.server.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    List<Measurement> findAllByWorkoutWorkoutId(Integer workoutId);

    List<Measurement> findAllByUserUserIdAndUserNotNull(Integer userId);
}
