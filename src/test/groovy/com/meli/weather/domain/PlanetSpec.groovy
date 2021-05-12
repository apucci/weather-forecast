package com.meli.weather.domain

import com.meli.weather.fixtures.PlanetFixtures
import spock.lang.Specification

class PlanetSpec extends Specification implements PlanetFixtures {

    void 'Should create a Planet'() {
        given: 'the attributes'
        def id = UUID.randomUUID().toString()
        def name = "Planet name"
        def speed = Math.random().intValue()
        def distance = Math.random().intValue()

        when: 'creates a new planet'
        def planet = new Planet(id, name, speed, distance)

        then: 'the attributes must be the same'
        noExceptionThrown()
        assert planet.id() == id
        assert planet.name() == name
        assert planet.speed() == speed
        assert planet.distance() == distance
        assert planet.location().properties == Location.standard(distance).properties
    }

    void 'Should create a Planet with no id'() {
        given: 'the attributes'
        def name = "Planet name"
        def speed = Math.random().intValue()
        def distance = Math.random().intValue()

        when: 'creates a new planet'
        def planet = new Planet(name, speed, distance)

        then: 'the attributes must be the same and id must be generated'
        noExceptionThrown()
        assert planet.id() != null
        assert planet.name() == name
        assert planet.speed() == speed
        assert planet.distance() == distance
        assert planet.location().properties == Location.standard(distance).properties
    }

    void 'Should move a Planet'() {
        given: 'a planet'
        def planet = planetDomain

        def originalId = planet.id()
        def originalName = planet.name()
        def originalSpeed = planet.speed()
        def originalDistance = planet.distance()
        def originalLocation = planet.location()

        when: 'its moved'
        planet.move()

        then: 'the planet location should be updated'
        noExceptionThrown()
        assert planet.id() == originalId
        assert planet.name() == originalName
        assert planet.speed() == originalSpeed
        assert planet.distance() == originalDistance
        assert planet.location().angle() == originalSpeed
        assert planet.location().xAxis() != originalLocation.xAxis()
        assert planet.location().yAxis() != originalLocation.yAxis()

    }

    void 'Should move a Planet by n days'() {
        given: 'a planet and a number of days'
        def days = 10
        def planet = planetDomain

        def originalId = planet.id()
        def originalName = planet.name()
        def originalSpeed = planet.speed()
        def originalDistance = planet.distance()
        def originalLocation = planet.location()

        when: 'its moved'
        planet.move(days)

        then: 'the planet location should be updated'
        noExceptionThrown()
        assert planet.id() == originalId
        assert planet.name() == originalName
        assert planet.speed() == originalSpeed
        assert planet.distance() == originalDistance
        assert planet.location().angle() == originalSpeed * days
        assert planet.location().xAxis() != originalLocation.xAxis()
        assert planet.location().yAxis() != originalLocation.yAxis()
    }
}
