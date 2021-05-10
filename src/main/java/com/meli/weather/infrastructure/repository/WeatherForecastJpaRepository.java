package com.meli.weather.infrastructure.repository;

import com.meli.weather.infrastructure.model.WeatherForecast;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.transaction.annotation.TransactionalAdvice;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
abstract class WeatherForecastJpaRepository implements CrudRepository<WeatherForecast, String> {

    private EntityManager entityMAnager;

    public WeatherForecastJpaRepository(EntityManager entityMAnager) {
        this.entityMAnager = entityMAnager;
    }

    @TransactionalAdvice
    void updateWeatherForGreatestRainIntensity(String weather) {
        entityMAnager
                .createNativeQuery("UPDATE weather_forecasts SET weather = ?  WHERE weather = 'RAIN' AND id IN (SELECT id FROM weather_forecasts WHERE rain_intensity in (SELECT MAX(rain_intensity) FROM weather_forecasts))")
                .setParameter(1, weather)
                .executeUpdate();
    }

    abstract Optional<WeatherForecast> findByDay(Integer day);
}
