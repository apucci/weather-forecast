package com.meli.weather.fixtures

import com.meli.weather.Database

trait PostgresFixtures {

    Map<String, Object> getPostgresConfiguration() {
        if (Database.postgresContainer == null || !Database.postgresContainer.isRunning()) {
            Database.init()
        }
        [
                'datasources.default.url'     : Database.postgresContainer.getJdbcUrl(),
                'datasources.default.password': Database.postgresContainer.getPassword(),
                'datasources.default.username': Database.postgresContainer.getUsername(),
        ]
    }
}