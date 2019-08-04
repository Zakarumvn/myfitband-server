package com.myfitband.server.dao;

import com.myfitband.server.entity.MeasurementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementTypeRepository extends JpaRepository<MeasurementType, Integer> {
}
