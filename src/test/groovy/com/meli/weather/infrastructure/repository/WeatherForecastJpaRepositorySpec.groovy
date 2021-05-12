package com.meli.weather.infrastructure.repository

import com.meli.weather.ApplicationContextBaseSpec
import com.meli.weather.domain.WeatherEnum
import com.meli.weather.fixtures.WeatherForecastFixtures
import com.meli.weather.infrastructure.model.WeatherForecast
import io.micronaut.transaction.annotation.TransactionalAdvice
import spock.lang.Subject

@TransactionalAdvice
class WeatherForecastJpaRepositorySpec extends ApplicationContextBaseSpec implements WeatherForecastFixtures {

    @Subject
    WeatherForecastJpaRepository jpaRepository = applicationContext.getBean(WeatherForecastJpaRepository)

    void 'should update a weather forecast to heavy rain'() {
        setup:
        def rainForecast = getWeatherForecastModel(WeatherEnum.RAIN, 1, BigDecimal.ONE)
        def heavyRainForecast = getWeatherForecastModel(WeatherEnum.RAIN, 2, BigDecimal.TEN)
        def standardForecast = getWeatherForecastModel(WeatherEnum.STANDARD, 3, BigDecimal.TEN)
        def droughtForecast = getWeatherForecastModel(WeatherEnum.DROUGHT, 4, BigDecimal.TEN)
        def perfectForecast = getWeatherForecastModel(WeatherEnum.PERFECT, 5, BigDecimal.TEN)

        persist(em, rainForecast, heavyRainForecast, standardForecast, droughtForecast, perfectForecast)

        when: 'updates the forecast'
        jpaRepository.updateWeatherForGreatestRainIntensity(WeatherEnum.HEAVY_RAIN.name())

        then: 'the only the highest intensity rain should become heavy rain'
        noExceptionThrown()
        def entities = jpaRepository.findAll()
        assert entities.count(i -> i.weather == WeatherEnum.HEAVY_RAIN) == 1
        assert (entities.find(i -> i.weather == WeatherEnum.HEAVY_RAIN) as WeatherForecast).day == 2

        cleanup:
        remove(em, rainForecast, heavyRainForecast, standardForecast, droughtForecast, perfectForecast)
    }

}
