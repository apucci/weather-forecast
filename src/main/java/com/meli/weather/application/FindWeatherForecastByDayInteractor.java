package com.meli.weather.application;

import com.meli.weather.application.output.WeatherForecastOutput;

public interface FindWeatherForecastByDayInteractor {
    WeatherForecastOutput execute(Integer day);
}
