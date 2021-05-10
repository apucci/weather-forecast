package com.meli.weather.application;

import com.meli.weather.application.inputs.ForecastTimeInput;

import java.math.BigDecimal;

public interface BasicWeatherForecastJobInteractor {
    void execute(ForecastTimeInput timeInput, BigDecimal errorMargin);
}
