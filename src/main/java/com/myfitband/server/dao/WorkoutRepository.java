package com.myfitband.server.dao;

import com.myfitband.server.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer> {

    List<Workout> findAllByOrderByStartDTDesc();

}
