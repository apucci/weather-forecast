package com.meli.weather.infrastructure.repository

import com.meli.weather.ApplicationContextBaseSpec
import com.meli.weather.commons.exception.NotFoundException
import com.meli.weather.domain.WeatherEnum
import com.meli.weather.domain.repository.WeatherForecastRepository
import com.meli.weather.fixtures.WeatherForecastFixtures
import com.meli.weather.infrastructure.model.WeatherForecast
import spock.lang.Subject

class WeatherForecastRepositorySpec extends ApplicationContextBaseSpec implements WeatherForecastFixtures {

    @Subject
    WeatherForecastRepository weatherForecastRepository = applicationContext.getBean(WeatherForecastRepository)

    void 'should create a weather forecast'() {
        given: 'a weather forecast'
        def wForecast = weatherForecastDomain

        when: 'tries to create a new forecast'
        def createdForecast = weatherForecastRepository.create(wForecast)

        then: 'the created forecast should be equal to the requested'
        noExceptionThrown()
        assert createdForecast.weather() == wForecast.weather()
        assert createdForecast.rainIntensity() == wForecast.rainIntensity()
        assert createdForecast.day() == wForecast.day()

        cleanup:
        def forecast = weatherForecastModel
        forecast.day = wForecast.day()

        remove(em, forecast)
    }

    void 'should find a weather forecast'() {
        given: 'a saved forecast and a day'
        def forecast = weatherForecastModel
        persist(em, forecast)
        def day = 1

        when: 'tries to find a forecast'
        def createdForecast = weatherForecastRepository.findByDay(day)

        then: 'the found forecast should match'
        noExceptionThrown()
        assert createdForecast.weather() == forecast.weather
        assert createdForecast.rainIntensity() == forecast.rainIntensity
        assert createdForecast.day() == forecast.day

        cleanup:
        remove(em, forecast)
    }

    void 'should throws a notFoundException when a weather forecast is not found for that day'() {
        given: 'a day'
        def day = 1

        when: 'tries to find a forecast'
        weatherForecastRepository.findByDay(day)

        then: 'the found forecast should match'
        def ex = thrown(NotFoundException)
        ex.message == 'No forecast found for the requested day'
    }


    void 'should update a weather forecast to heavy rain'() {
        setup:
        def rainForecast = getWeatherForecastModel(WeatherEnum.RAIN, 1, BigDecimal.ONE)
        def heavyRainForecast = getWeatherForecastModel(WeatherEnum.RAIN, 2, BigDecimal.TEN)
        def standardForecast = getWeatherForecastModel(WeatherEnum.STANDARD, 3, BigDecimal.TEN)
        def droughtForecast = getWeatherForecastModel(WeatherEnum.DROUGHT, 4, BigDecimal.TEN)
        def perfectForecast = getWeatherForecastModel(WeatherEnum.PERFECT, 5, BigDecimal.TEN)

        persist(em, rainForecast, heavyRainForecast, standardForecast, droughtForecast, perfectForecast)

        when: 'updates the forecast'
        weatherForecastRepository.updateHeavyRainDays()

        then: 'the only the highest intensity rain should become heavy rain'
        noExceptionThrown()
        def entities = entityManager.createQuery("FROM WeatherForecast", WeatherForecast).getResultList()
        assert entities.count { it.weather == WeatherEnum.HEAVY_RAIN } == 1
        assert entities.find { it.weather == WeatherEnum.HEAVY_RAIN }.day == 2

        cleanup:
        remove(em, rainForecast, heavyRainForecast, standardForecast, droughtForecast, perfectForecast)
    }

}
