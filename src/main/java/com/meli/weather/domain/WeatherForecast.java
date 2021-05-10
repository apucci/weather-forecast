package com.meli.weather.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class WeatherForecast {

    private String id;

    private Integer day;

    private WeatherEnum weather;

    private BigDecimal rainIntensity;

    public WeatherForecast(String id, Integer day, WeatherEnum weather, BigDecimal rainIntensity) {
        this.id = id;
        this.day = day;
        this.weather = weather;
        this.rainIntensity = rainIntensity;
    }

    public WeatherForecast(Integer day, WeatherEnum weather, BigDecimal rainIntensity) {
        this.id = UUID.randomUUID().toString();
        this.day = day;
        this.weather = weather;
        this.rainIntensity = rainIntensity;
    }

    public String id() {
        return id;
    }

    public Integer day() {
        return day;
    }

    public WeatherEnum weather() {
        return weather;
    }

    public BigDecimal rainIntensity() {
        return rainIntensity;
    }
}
