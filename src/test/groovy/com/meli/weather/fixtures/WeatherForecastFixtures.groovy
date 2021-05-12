package com.meli.weather.fixtures

import com.meli.weather.domain.WeatherEnum
import com.meli.weather.infrastructure.model.WeatherForecast

trait WeatherForecastFixtures {

    WeatherForecast getWeatherForecastModel() {
        def wForecast = new WeatherForecast()
        wForecast.day = 1
        wForecast.rainIntensity = BigDecimal.ONE
        wForecast.weather = WeatherEnum.RAIN

        return wForecast
    }

    WeatherForecast getWeatherForecastModel(WeatherEnum weather, Integer day, BigDecimal rainIntensity) {
        def wForecast = new WeatherForecast()
        wForecast.day = day
        wForecast.rainIntensity = rainIntensity
        wForecast.weather = weather

        return wForecast
    }

    WeatherForecast getWeatherForecastModel(Integer day) {
        def wForecast = new WeatherForecast()
        wForecast.day = day
        wForecast.rainIntensity = BigDecimal.ONE
        wForecast.weather = WeatherEnum.RAIN

        return wForecast
    }

    com.meli.weather.domain.WeatherForecast getWeatherForecastDomain() {
        return new com.meli.weather.domain.WeatherForecast(1, WeatherEnum.RAIN, BigDecimal.TEN)
    }
}