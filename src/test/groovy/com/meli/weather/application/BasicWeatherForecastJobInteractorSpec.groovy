package com.meli.weather.application

import com.meli.weather.ApplicationContextBaseSpec
import com.meli.weather.application.inputs.ForecastTimeInput
import com.meli.weather.commons.exception.ForecastException
import com.meli.weather.domain.WeatherEnum
import com.meli.weather.fixtures.PlanetFixtures
import com.meli.weather.fixtures.WeatherForecastFixtures
import com.meli.weather.infrastructure.model.WeatherForecast
import spock.lang.Subject

class BasicWeatherForecastJobInteractorSpec extends ApplicationContextBaseSpec implements WeatherForecastFixtures, PlanetFixtures {

    @Subject
    BasicWeatherForecastJobInteractor forecastJobInteractor = applicationContext.getBean(BasicWeatherForecastJobInteractor)

    void 'should throws exception when have less than 3 planets'() {
        given: 'a saved forecast'
        def vPlanet = vulcanoModel
        remove(em, vPlanet)

        when: 'tries to execute'
        forecastJobInteractor.execute(new ForecastTimeInput(16, 10), BigDecimal.valueOf(0.001))

        then: 'an exception should be thrown'
        def ex = thrown(ForecastException)
        assert ex.message == 'Must have exact 3 planets'

        cleanup:
        persist(em, vPlanet)
    }

    void 'should foresee'() {
        given: 'a time input'
        def input = new ForecastTimeInput(16, 10)

        when: 'execute'
        forecastJobInteractor.execute(input, BigDecimal.valueOf(0.001))

        then: 'should fill the forecast table'
        noExceptionThrown()
        def forecasts = em.createQuery("FROM WeatherForecast ORDER BY day", WeatherForecast).resultList
        assert forecasts.size() == 161
        assert forecasts[116].weather == WeatherEnum.PERFECT
        assert forecasts[0].weather == WeatherEnum.DROUGHT
        assert forecasts[72].weather == WeatherEnum.HEAVY_RAIN
        assert forecasts[73].weather == WeatherEnum.RAIN
    }
}
