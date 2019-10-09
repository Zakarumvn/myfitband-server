package com.myfitband.server.dao;

import com.myfitband.server.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<Setting, Integer> {
        Setting findByUserUserId(Integer userId);
}
