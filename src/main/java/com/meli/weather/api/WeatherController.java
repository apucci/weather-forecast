package com.meli.weather.api;

import com.meli.weather.application.BasicWeatherForecastJobInteractor;
import com.meli.weather.application.FindWeatherForecastByDayInteractor;
import com.meli.weather.application.inputs.ForecastTimeInput;
import com.meli.weather.application.output.WeatherForecastOutput;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;

import java.math.BigDecimal;

@Controller("/weather")
public class WeatherController {

    private BasicWeatherForecastJobInteractor interactor;
    private FindWeatherForecastByDayInteractor findWeatherForecastByDay;

    public WeatherController(BasicWeatherForecastJobInteractor interactor, FindWeatherForecastByDayInteractor findWeatherForecastByDay) {
        this.interactor = interactor;
        this.findWeatherForecastByDay = findWeatherForecastByDay;
    }

    @Post
    public void process() {
        interactor.execute(new ForecastTimeInput(360, 10), BigDecimal.valueOf(0.001D));
    }

    @Get(value = "/{day}", produces = MediaType.APPLICATION_JSON)
    public WeatherForecastOutput getWeatherByDay(@PathVariable Integer day) {
        return findWeatherForecastByDay.execute(day);
    }

}
