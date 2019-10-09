package com.myfitband.server.dao;


import com.myfitband.server.entity.GPSdata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GPSDataRepository extends JpaRepository<GPSdata, Integer> {
    List<GPSdata> findAllByWorkoutWorkoutIdOrderByDateAsc(Integer workoutId);

}
