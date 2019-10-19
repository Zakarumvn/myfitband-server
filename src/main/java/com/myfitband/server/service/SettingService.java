package com.myfitband.server.service;

import com.myfitband.server.dao.AlertRepository;
import com.myfitband.server.dao.PhysicalPropertiesRepository;
import com.myfitband.server.dao.SettingRepository;
import com.myfitband.server.entity.Alert;
import com.myfitband.server.entity.PhysicalProperties;
import com.myfitband.server.entity.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingService {

    @Autowired
    SettingRepository settingRepository;

    @Autowired
    PhysicalPropertiesRepository physicalPropertiesRepository;

    @Autowired
    AlertRepository alertRepository;

    public Setting getNotificationSettingsByUserId(Integer userId){
        return settingRepository.findByUserUserId(userId);
    }

    public void saveNotificationSettings(Setting setting){
        settingRepository.save(setting);
    }

    public PhysicalProperties loadPhysicalProperties(Integer userID){
        return physicalPropertiesRepository.findByUserUserId(userID);
    }

    public List<Alert> loadAlerts(Integer userId){
        return alertRepository.findByUserUserId(userId);
    }



}
