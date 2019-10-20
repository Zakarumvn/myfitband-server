package com.myfitband.server.service;

import com.myfitband.server.dao.SportRepository;
import com.myfitband.server.entity.Sport;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SportService {

    private final SportRepository sportRepository;

    public SportService(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    public Optional<Sport> findSportByName(String name) {
        return sportRepository.findAll().stream()
                .filter(s -> name.equalsIgnoreCase(s.getName()))
                .findFirst();
    }
}
