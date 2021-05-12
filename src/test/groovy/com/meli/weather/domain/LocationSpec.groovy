package com.meli.weather.domain

import spock.lang.Specification

import java.math.RoundingMode

class LocationSpec extends Specification {


    void 'Should get sun location'() {
        when: 'gets sun location '
        def location = Location.sunLocation()

        then: 'the position must be defined'
        noExceptionThrown()
        assert location.angle() == 0
        assert location.xAxis() == BigDecimal.ZERO
        assert location.yAxis() == BigDecimal.ZERO
    }

    void 'Should get standard location'() {
        given: 'a random position'
        def position = Math.random().intValue()

        when: 'gets standard location '
        def location = Location.standard(position)

        then: 'the position must be defined'
        noExceptionThrown()
        assert location.angle() == 0
        assert location.xAxis() == BigDecimal.valueOf(position)
        assert location.yAxis() == BigDecimal.ZERO
    }

    void 'Should get location given a position'() {
        given: 'an angle and a radius'
        Integer angle = Math.random().intValue()
        def radius = 500

        when: 'gets location from these information'
        def location = Location.fromMovement(angle, radius)

        then: 'the position must be valid'
        noExceptionThrown()

        assert location.angle() == angle
        assert location.xAxis().scale() == 5
        assert location.yAxis().scale() == 5
        assert location.xAxis() == BigDecimal.valueOf(Math.cos(Math.toRadians(angle)) * radius).setScale(5, RoundingMode.DOWN)
        assert location.yAxis() == BigDecimal.valueOf(Math.sin(Math.toRadians(angle)) * radius).setScale(5, RoundingMode.DOWN)
    }

}
