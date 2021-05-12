package com.meli.weather.application

import com.meli.weather.ApplicationContextBaseSpec
import com.meli.weather.commons.exception.NotFoundException
import com.meli.weather.fixtures.WeatherForecastFixtures
import spock.lang.Subject

class FindWeatherForecastByDayInteractorSpec extends ApplicationContextBaseSpec implements WeatherForecastFixtures {

    @Subject
    FindWeatherForecastByDayInteractor forecastByDayInteractor = applicationContext.getBean(FindWeatherForecastByDayInteractor)

    void 'should find a forecast by day'() {
        given: 'a saved forecast'
        def forecast = weatherForecastModel
        persist(em, forecast)

        when: 'tries to find'
        def result = forecastByDayInteractor.execute(forecast.day)

        then: 'should return the forecast for the day'
        noExceptionThrown()

        assert result.day == forecast.day
        assert result.weather == forecast.weather

        cleanup:
        remove(em, forecast)
    }

    void 'should throw notFoundException when the day was not foreseen'() {
        when: 'tries to find'
        forecastByDayInteractor.execute(10)

        then: 'should throws the exception'
        def ex = thrown(NotFoundException)

        assert ex.message == 'No forecast found for the requested day'
    }
}
