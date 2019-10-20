package com.myfitband.server.dao;

import com.myfitband.server.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Integer> {
    List<Alert> findByUserUserId(Integer userId);
}

