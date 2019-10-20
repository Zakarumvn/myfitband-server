package com.myfitband.server.service.utils;

import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MeasurementDtoUtils {

    private static DateTimeFormatter pulseTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static DateTimeFormatter weightDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String getHourAsString(LocalDateTime dateTime){
        return dateTime.format(pulseTimeFormatter);
    }

    public static String getDateAsString(LocalDateTime dateTime){
        return dateTime.format(weightDateFormatter);
    }

    public static String getWeightString(String weight){
        if(!StringUtils.isEmpty(weight) && weight.length() > 4) {
            return weight.substring(0, 4);
        }

        return weight;
    }
}
