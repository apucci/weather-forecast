package com.meli.weather.infrastructure.service

import com.meli.weather.fixtures.PlanetFixtures
import com.meli.weather.domain.Planet
import spock.lang.Specification
import spock.lang.Subject

class PlanetServiceSpec extends Specification implements PlanetFixtures {

    @Subject
    private PlanetService planetService = new PlanetService()

    void 'Should create a PlanetDomain object when passes a PlanetModel object'() {
        given: 'a planet model'
        def planet = planetModel

        when: 'converting it to a domain object'
        def result = planetService.fromModelToDomain(planet)

        then: 'the attributes must maintain the same'
        noExceptionThrown()
        assert result as Planet
        assert result.id() == planet.id
        assert result.name() == planet.name
        assert result.distance() == planet.distance
        assert result.speed() == planet.speed
    }


}
