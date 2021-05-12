package com.meli.weather

import org.testcontainers.containers.PostgreSQLContainer

abstract class Database {

    static PostgreSQLContainer postgresContainer

    static init() {
        if (postgresContainer == null) {
            postgresContainer = new PostgreSQLContainer<>('postgres:alpine')
                    .withDatabaseName('test_database')
                    .withUsername('test_user')
                    .withPassword('test_passwd')

            postgresContainer.start()
        }
    }

}
