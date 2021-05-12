package com.meli.weather.fixtures

import com.meli.weather.infrastructure.model.WeatherForecast

import javax.persistence.EntityManager

trait EntityManagerFixtures {

    void persist(EntityManager entityManager, Object... objects) {
        entityManager.transaction.begin()
        for (Object o : objects) {
            entityManager.persist(o)
        }
        entityManager.transaction.commit()
    }

    void remove(EntityManager entityManager, Object... objects) {
        entityManager.transaction.begin()
        for (Object o : objects) {
            entityManager.remove(o)
        }
        entityManager.transaction.commit()
    }

    void clear(EntityManager entityManager) {
        remove(entityManager, entityManager.createQuery("FROM WeatherForecast", WeatherForecast).resultList.toArray())
    }

}