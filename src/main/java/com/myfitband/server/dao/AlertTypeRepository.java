package com.myfitband.server.dao;

import com.myfitband.server.entity.AlertType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertTypeRepository extends JpaRepository<AlertType, Integer> {
}
