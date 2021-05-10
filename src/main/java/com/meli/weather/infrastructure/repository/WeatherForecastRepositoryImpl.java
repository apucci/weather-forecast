package com.meli.weather.infrastructure.repository;

import com.meli.weather.commons.exception.NotFoundException;
import com.meli.weather.domain.WeatherEnum;
import com.meli.weather.domain.WeatherForecast;
import com.meli.weather.domain.repository.WeatherForecastRepository;
import com.meli.weather.infrastructure.service.WeatherService;

import javax.inject.Singleton;

@Singleton
public class WeatherForecastRepositoryImpl implements WeatherForecastRepository {

    private WeatherForecastJpaRepository jpaRepository;

    private WeatherService weatherService;

    public WeatherForecastRepositoryImpl(WeatherForecastJpaRepository jpaRepository, WeatherService weatherService) {
        this.jpaRepository = jpaRepository;
        this.weatherService = weatherService;
    }

    @Override
    public WeatherForecast create(WeatherForecast weatherForecast) {
        return weatherService.fromModelToDomain(jpaRepository.save(weatherService.fromDomainToModel(weatherForecast)));
    }

    @Override
    public void updateHeavyRainDays() {
        jpaRepository.updateWeatherForGreatestRainIntensity(WeatherEnum.HEAVY_RAIN.name());
    }

    @Override
    public WeatherForecast findByDay(Integer day) {
        return weatherService.fromModelToDomain(jpaRepository.findByDay(day)
                .orElseThrow(() -> new NotFoundException("No forecast found for the requested day")));
    }
}
