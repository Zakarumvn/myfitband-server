package com.myfitband.server.service.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PulseChartUtils {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static String getHourAsString(LocalDateTime dateTime){
        return dateTime.format(formatter);
    }
}
