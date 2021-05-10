package com.meli.weather.application.impl;

import com.meli.weather.application.FindWeatherForecastByDayInteractor;
import com.meli.weather.application.output.WeatherForecastOutput;
import com.meli.weather.domain.repository.WeatherForecastRepository;

import javax.inject.Singleton;

@Singleton
public class FindWeatherForecastByDayInteractorImpl implements FindWeatherForecastByDayInteractor {

    private WeatherForecastRepository weatherForecastRepository;

    public FindWeatherForecastByDayInteractorImpl(WeatherForecastRepository weatherForecastRepository) {
        this.weatherForecastRepository = weatherForecastRepository;
    }

    @Override
    public WeatherForecastOutput execute(Integer day) {
        var weatherForecast = weatherForecastRepository.findByDay(day);
        return new WeatherForecastOutput(weatherForecast.weather(), weatherForecast.day());
    }
}
