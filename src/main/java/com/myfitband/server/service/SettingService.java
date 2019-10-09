package com.myfitband.server.service;

import com.myfitband.server.dao.SettingRepository;
import com.myfitband.server.entity.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingService {

    @Autowired
    SettingRepository settingRepository;

    public Setting getNotificationSettingsByUserId(Integer userId){
        return settingRepository.findByUserUserId(userId);
    }

    public void saveNotificationSettings(Setting setting){
        settingRepository.save(setting);
    }
}
