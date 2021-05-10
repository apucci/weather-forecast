package com.meli.weather.infrastructure.service;

import com.meli.weather.infrastructure.model.WeatherForecast;

import javax.inject.Singleton;

@Singleton
public class WeatherService {

    public WeatherForecast fromDomainToModel(com.meli.weather.domain.WeatherForecast weatherDomain) {
        var weatherModel = new com.meli.weather.infrastructure.model.WeatherForecast();
        weatherModel.setId(weatherDomain.id());
        weatherModel.setWeather(weatherDomain.weather());
        weatherModel.setRainIntensity(weatherDomain.rainIntensity());
        weatherModel.setDay(weatherDomain.day());

        return weatherModel;
    }

    public com.meli.weather.domain.WeatherForecast fromModelToDomain(WeatherForecast weatherModel) {
        return new com.meli.weather.domain.WeatherForecast(weatherModel.getId(), weatherModel.getDay(), weatherModel.getWeather(), weatherModel.getRainIntensity());
    }

}
