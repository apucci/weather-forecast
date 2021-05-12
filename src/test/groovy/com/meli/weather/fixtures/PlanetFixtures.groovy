package com.meli.weather.fixtures

import com.meli.weather.infrastructure.model.Planet

trait PlanetFixtures {

    Planet getPlanetModel() {
        def planet = new Planet()
        planet.setId(UUID.randomUUID().toString())
        planet.setDistance(100)
        planet.setName("test planet")
        planet.setSpeed(2)

        return planet
    }

    Planet getVulcanoModel() {
        def planet = new Planet()
        planet.setId("8f0d42fd-0c1a-45b7-b774-6a415aa17ac8")
        planet.setSpeed(5)
        planet.setName("Vulcano")
        planet.setDistance(1000)

        return planet
    }

    Planet getFerengiModel() {
        def planet = new Planet()
        planet.setId("a19ac633-108a-45b5-91b4-9a33e2922aac")
        planet.setSpeed(-1)
        planet.setName("Ferengi")
        planet.setDistance(500)

        return planet
    }

    Planet getBetasoideModel() {
        def planet = new Planet()
        planet.setId("455f7fb3-668f-46e3-88e4-2cddff28c394")
        planet.setSpeed(-3)
        planet.setName("Betasoide")
        planet.setDistance(2000)

        return planet
    }

    com.meli.weather.domain.Planet getPlanetDomain() {
        return new com.meli.weather.domain.Planet(UUID.randomUUID().toString(), "Planet Name", 3, 90)
    }

    com.meli.weather.domain.Planet getPlanetDomain(Integer speed, Integer distance) {
        return new com.meli.weather.domain.Planet(UUID.randomUUID().toString(), "Planet Name", speed, distance)
    }

    com.meli.weather.domain.Planet getVulcanoDomain() {
        return new com.meli.weather.domain.Planet('8f0d42fd-0c1a-45b7-b774-6a415aa17ac8', 'Vulcano', 5, 1000)
    }

    com.meli.weather.domain.Planet getFerengiDomain() {
        return new com.meli.weather.domain.Planet('a19ac633-108a-45b5-91b4-9a33e2922aac', 'Ferengi', -1, 500)
    }

    com.meli.weather.domain.Planet getBetasoideDomain() {
        return new com.meli.weather.domain.Planet('455f7fb3-668f-46e3-88e4-2cddff28c394', 'Betasoide', -3, 2000)
    }


}
