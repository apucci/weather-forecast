package com.meli.weather.application

import com.meli.weather.ApplicationContextBaseSpec
import com.meli.weather.fixtures.WeatherForecastFixtures
import com.meli.weather.infrastructure.model.WeatherForecast
import spock.lang.Subject

class ClearWeatherForecastInteractorSpec extends ApplicationContextBaseSpec implements WeatherForecastFixtures {

    @Subject
    ClearWeatherForecastInteractor clearWeatherForecastInteractor = applicationContext.getBean(ClearWeatherForecastInteractor)

    void 'should clear the forecasts'() {
        given: 'a group of already existent forecasts'
        def forecast1 = getWeatherForecastModel(1)
        def forecast2 = getWeatherForecastModel(2)
        def forecast3 = getWeatherForecastModel(3)
        persist(em, forecast1, forecast2, forecast3)

        when: 'clears'
        clearWeatherForecastInteractor.execute()

        then: 'no forecast should exist'
        noExceptionThrown()
        def forecasts = em.createQuery("FROM WeatherForecast ORDER BY day", WeatherForecast).resultList
        assert forecasts.isEmpty()
    }
}
