package com.meli.weather.infrastructure.repository

import com.meli.weather.ApplicationContextBaseSpec
import com.meli.weather.fixtures.PlanetFixtures
import spock.lang.Subject

class PlanetJpaRepositorySpec extends ApplicationContextBaseSpec implements PlanetFixtures {

    @Subject
    PlanetJpaRepository planetJpaRepository = applicationContext.getBean(PlanetJpaRepository)

    void 'should get first three planets'() {
        when: 'request to get original planets'
        def planets = planetJpaRepository.firstThreePlanets()

        then: 'the 3 original planets should be returned'
        noExceptionThrown()
        assert planets.size() == 3
        assert planets.find { it.id == '8f0d42fd-0c1a-45b7-b774-6a415aa17ac8' }.properties == vulcanoModel.properties
        assert planets.find { it.id == 'a19ac633-108a-45b5-91b4-9a33e2922aac' }.properties == ferengiModel.properties
        assert planets.find { it.id == '455f7fb3-668f-46e3-88e4-2cddff28c394' }.properties == betasoideModel.properties
    }

}
