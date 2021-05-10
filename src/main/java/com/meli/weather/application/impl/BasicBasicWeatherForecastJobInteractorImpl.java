package com.meli.weather.application.impl;

import com.meli.weather.application.BasicWeatherForecastJobInteractor;
import com.meli.weather.application.inputs.ForecastTimeInput;
import com.meli.weather.commons.exception.ForecastException;
import com.meli.weather.domain.WeatherEnum;
import com.meli.weather.domain.WeatherForecast;
import com.meli.weather.domain.repository.PlanetRepository;
import com.meli.weather.domain.repository.WeatherForecastRepository;
import com.meli.weather.domain.service.WeatherService;

import javax.inject.Singleton;
import java.math.BigDecimal;

@Singleton
public class BasicBasicWeatherForecastJobInteractorImpl implements BasicWeatherForecastJobInteractor {

    private PlanetRepository planetRepository;

    private WeatherForecastRepository weatherForecastRepository;

    private WeatherService weatherService;

    public BasicBasicWeatherForecastJobInteractorImpl(PlanetRepository planetRepository, WeatherForecastRepository weatherForecastRepository, WeatherService weatherService) {
        this.planetRepository = planetRepository;
        this.weatherForecastRepository = weatherForecastRepository;
        this.weatherService = weatherService;
    }

    @Override
    public void execute(ForecastTimeInput timeInput, BigDecimal errorMargin) {
        var originalPlanets = planetRepository.getOriginalPlanets();

        if (originalPlanets.size() < 3) {
            throw new ForecastException("Insufficient Planets");
        }

        var currentDay = 0;
        while (currentDay <= timeInput.daysPerYear() * timeInput.numberOfYears()) {
            if (currentDay != 0) {
                originalPlanets.get(0).move();
                originalPlanets.get(1).move();
                originalPlanets.get(2).move();
            }

            var weather = weatherService.forecastWeather(originalPlanets.get(0), originalPlanets.get(1), originalPlanets.get(2), errorMargin);
            var rainIntensity = BigDecimal.ZERO;
            if (WeatherEnum.RAIN == weather) {
                rainIntensity = weatherService.calculateRainIntensity(originalPlanets.get(0), originalPlanets.get(1), originalPlanets.get(2));
            }

            weatherForecastRepository.create(new WeatherForecast(currentDay, weather, rainIntensity));
            currentDay++;
        }

        weatherForecastRepository.updateHeavyRainDays();
    }

}
