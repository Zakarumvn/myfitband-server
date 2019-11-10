package com.myfitband.server.service;

import com.myfitband.server.dao.AlertRepository;
import com.myfitband.server.dao.PhysicalPropertiesRepository;
import com.myfitband.server.dao.SettingRepository;
import com.myfitband.server.entity.Alert;
import com.myfitband.server.entity.PhysicalProperties;
import com.myfitband.server.entity.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SettingService {

    @Autowired
    SettingRepository settingRepository;

    @Autowired
    PhysicalPropertiesRepository physicalPropertiesRepository;

    @Autowired
    AlertRepository alertRepository;

    public Setting getNotificationSettingsByUserId(Integer userId) {
        return settingRepository.findByUserUserId(userId);
    }

    public void saveNotificationSettings(Setting setting) {
        settingRepository.save(setting);
    }

    public PhysicalProperties loadPhysicalProperties(Integer userID) {
        return physicalPropertiesRepository.findByUserUserId(userID);
    }

    public List<Alert> loadAlerts(Integer userId) {
        return alertRepository.findByUserUserIdOrderByDateDesc(userId);
    }

    public List<Setting> getNotificationFrom(LocalDateTime compareTime) {
        return settingRepository.findAll().stream()
                .filter(setting -> equalsTime(compareTime, toLocalDateTime(setting.getNotificationTime())))
                .collect(Collectors.toList());
    }

    private boolean equalsTime(LocalDateTime d1, LocalDateTime d2) {
        return d1.getHour() == d2.getHour() && d1.getMinute() == d2.getMinute() && d1.getSecond() == d2.getSecond();
    }

    private LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

}
