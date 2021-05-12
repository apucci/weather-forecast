package com.meli.weather.infrastructure.repository

import com.meli.weather.ApplicationContextBaseSpec
import com.meli.weather.domain.repository.PlanetRepository
import com.meli.weather.fixtures.PlanetFixtures
import spock.lang.Subject

class PlanetRepositorySpec extends ApplicationContextBaseSpec implements PlanetFixtures {

    @Subject
    PlanetRepository planetRepository = applicationContext.getBean(PlanetRepository)

    void 'should get original planets'() {
        given: 'the three original planets'

        def f = ferengiDomain
        def v = vulcanoDomain
        def b = betasoideDomain

        when: 'request to get original planets'
        def planets = planetRepository.originalPlanets

        then: 'the 3 original planets should be returned'
        noExceptionThrown()
        assert planets.size() == 3

        def vulcano = planets.find { it.id() == '8f0d42fd-0c1a-45b7-b774-6a415aa17ac8' }
        assert vulcano.name() == v.name()
        assert vulcano.speed() == v.speed()
        assert vulcano.distance() == v.distance()

        def ferengi = planets.find { it.id() == 'a19ac633-108a-45b5-91b4-9a33e2922aac' }
        assert ferengi.name() == f.name()
        assert ferengi.speed() == f.speed()
        assert ferengi.distance() == f.distance()

        def betasoide = planets.find { it.id() == '455f7fb3-668f-46e3-88e4-2cddff28c394' }
        assert betasoide.name() == b.name()
        assert betasoide.speed() == b.speed()
        assert betasoide.distance() == b.distance()
    }

}
