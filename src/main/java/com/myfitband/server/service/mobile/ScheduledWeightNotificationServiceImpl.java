package com.myfitband.server.service.mobile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.myfitband.server.dao.DeviceRepository;
import com.myfitband.server.entity.Device;
import com.myfitband.server.entity.Setting;
import com.myfitband.server.service.SettingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@EnableAsync
@EnableScheduling
public class ScheduledWeightNotificationServiceImpl implements ScheduledWeightNotificationService {

    private final SettingService settingService;
    private final DeviceRepository deviceRepository;

    @Value("${firebase.server.key}")
    private String firebaseServerKey;
    @Value("${firebase.server.url}")
    private String firebaseUrl;

    public ScheduledWeightNotificationServiceImpl(SettingService settingService, DeviceRepository deviceRepository) {
        this.settingService = settingService;
        this.deviceRepository = deviceRepository;
    }

    // cron expression - funkcja powinna sie uruchamiac sie co minute
    @Override
    @Scheduled(cron = "0 * * ? * *")
    public void pushNotifications() {
        // pobranie bierzacej daty i godziny
        LocalDateTime now = LocalDateTime.now();
        // pobranie ustawien powiadomien ktore powinny zostac wyslane o podanej godzinie i w danej minucie
        List<Setting> settings = settingService.getNotificationFrom(now);
        // kazde powiadomienie dla tejze godziny
        settings.forEach(s -> {
            // pobranie skojarzonego tokenu tokenu uzytkownika
            Optional<Device> device = deviceRepository.findAll().stream()
                    .filter(d -> d.getUser().getUserId().equals(s.getUser().getUserId()))
                    .findFirst();
            // jezeli udalo sie znalezc token firebase dla uzytkownika
            if (device.isPresent()) {
                try {
                    // wysylane jest zapytanie POST - url: https://fcm.googleapis.com/fcm/send
                    Unirest.post(firebaseUrl)
                            // dodanie naglowkow do zapytania HTTP
                            .header("content-type", "application/json")
                            .header("authorization", firebaseServerKey) // wraz z kluczem aplikacji wygenerowanym
                            // w konsoli firebase podczas rejestracji uslugi
                            .header("cache-control", "no-cache")
                            // body requestu - serializowany FirebaseMessage: token, tytul i tresc powiadomienia
                            .body(serialize(new FirebaseMessage(device.get().getFireBaseToken(),
                                    "MyFitBand - przypomnienie", "Czas na pomiar wagi!")))
                            .asString();
                } catch (UnirestException | JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String serialize(FirebaseMessage firebaseMessage) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(firebaseMessage);
    }
}
