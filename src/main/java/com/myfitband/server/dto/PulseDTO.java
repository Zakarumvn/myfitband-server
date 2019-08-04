package com.myfitband.server.dto;

public class PulseDTO {

    private String date;

    private String value;

    public PulseDTO(){}

    public PulseDTO(String date, String value) {
        this.date = date;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
