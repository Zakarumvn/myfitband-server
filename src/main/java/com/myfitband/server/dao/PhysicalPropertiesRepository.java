package com.myfitband.server.dao;

import com.myfitband.server.entity.PhysicalProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhysicalPropertiesRepository extends JpaRepository<PhysicalProperties, Integer> {

    PhysicalProperties findByUserUserId(Integer userId);
}
