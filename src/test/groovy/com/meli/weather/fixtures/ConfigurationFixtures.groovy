package com.meli.weather.fixtures

trait ConfigurationFixtures implements PostgresFixtures {

    Map<String, Object> getConfiguration() {
        Map<String, Object> m = [:]
        m += postgresConfiguration

        m
    }


}