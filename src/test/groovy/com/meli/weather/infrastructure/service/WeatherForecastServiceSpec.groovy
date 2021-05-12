package com.meli.weather.infrastructure.service

import com.meli.weather.domain.WeatherForecast
import com.meli.weather.fixtures.WeatherForecastFixtures
import spock.lang.Specification
import spock.lang.Subject

class WeatherForecastServiceSpec extends Specification implements WeatherForecastFixtures {

    @Subject
    private WeatherForecastService weatherForecastService = new WeatherForecastService()

    void 'Should create a WeatherForecastDomain object when passes a WeatherForecastModel object'() {
        given: 'a model object'
        def forecastModel = weatherForecastModel

        when: 'converting to a domain object'
        def result = weatherForecastService.fromModelToDomain(forecastModel)

        then: 'the attributes must remain the same'
        noExceptionThrown()
        assert result as WeatherForecast
        assert result.day() == forecastModel.day
        assert result.rainIntensity() == forecastModel.rainIntensity
        assert result.weather() == forecastModel.weather
    }

    void 'Should create a WeatherForecastModel object when passes a WeatherForecastDomain object'() {
        given: 'a domain object'
        def forecastDomain = weatherForecastDomain

        when: 'converting to a model object'
        def result = weatherForecastService.fromDomainToModel(forecastDomain)

        then: 'the attributes must remain the same'
        noExceptionThrown()
        assert result as com.meli.weather.infrastructure.model.WeatherForecast
        assert forecastDomain.day() == result.day
        assert forecastDomain.rainIntensity() == result.rainIntensity
        assert forecastDomain.weather() == result.weather
    }

}
