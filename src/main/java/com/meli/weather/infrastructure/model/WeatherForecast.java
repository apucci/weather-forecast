package com.meli.weather.infrastructure.model;

import com.meli.weather.domain.WeatherEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "weather_forecasts")
public class WeatherForecast {

    @Id
    private String id;

    private Integer day;

    @Enumerated(EnumType.STRING)
    private WeatherEnum weather;

    private BigDecimal rainIntensity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public WeatherEnum getWeather() {
        return weather;
    }

    public void setWeather(WeatherEnum weather) {
        this.weather = weather;
    }

    public BigDecimal getRainIntensity() {
        return rainIntensity;
    }

    public void setRainIntensity(BigDecimal rainIntensity) {
        this.rainIntensity = rainIntensity;
    }
}
