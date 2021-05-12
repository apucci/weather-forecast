package com.meli.weather.domain.service

import com.meli.weather.domain.WeatherEnum
import com.meli.weather.fixtures.PlanetFixtures
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class WeatherServiceSpec extends Specification implements PlanetFixtures {

    @Subject
    WeatherService weatherService = new WeatherService()

    @Unroll
    void 'should calculate rain intensity'() {
        given: 'three planets'
        def planetF = getPlanetDomain(-1, 500)
        def planetV = getPlanetDomain(5, 1000)
        def planetB = getPlanetDomain(-3, 2000)

        planetF.move(day)
        planetV.move(day)
        planetB.move(day)

        when: 'calculate the rain intensity'
        def rainIntensity = weatherService.calculateRainIntensity(planetF, planetV, planetB)

        then: 'the result must be correct'
        noExceptionThrown()
        assert rainIntensity == perimeter

        where:
        day  | perimeter
        72   | BigDecimal.valueOf(6262.3008)
        73   | BigDecimal.valueOf(6258.143)
        1087 | BigDecimal.valueOf(3893.9076)
    }

    @Unroll
    void 'should forecast the weather'() {
        given: 'three planets'
        def planetF = getPlanetDomain(-1, 500)
        def planetV = getPlanetDomain(5, 1000)
        def planetB = getPlanetDomain(-3, 2000)

        planetF.move(day)
        planetV.move(day)
        planetB.move(day)

        when: 'forecast the weather'
        def weather = weatherService.forecastWeather(planetF, planetV, planetB, BigDecimal.valueOf(0.001D))

        then: 'the result must be correct'
        noExceptionThrown()
        assert weather == expectWeather

        where:
        day | expectWeather
        566 | WeatherEnum.RAIN
        36  | WeatherEnum.STANDARD
        720 | WeatherEnum.DROUGHT
        116 | WeatherEnum.PERFECT
    }

}
