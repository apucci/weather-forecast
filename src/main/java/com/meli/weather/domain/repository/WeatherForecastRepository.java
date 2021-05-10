package com.meli.weather.domain.repository;

import com.meli.weather.domain.WeatherForecast;

public interface WeatherForecastRepository {

    WeatherForecast create(WeatherForecast weatherForecast);

    void updateHeavyRainDays();

    WeatherForecast findByDay(Integer day);

}
