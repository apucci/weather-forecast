package com.meli.weather

import com.meli.weather.fixtures.ConfigurationFixtures
import com.meli.weather.fixtures.EntityManagerFixtures
import io.micronaut.context.ApplicationContext
import org.hibernate.SessionFactory
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import javax.persistence.EntityManager

abstract class ApplicationContextBaseSpec extends Specification implements ConfigurationFixtures, EntityManagerFixtures {

    @AutoCleanup
    @Shared
    ApplicationContext applicationContext = ApplicationContext.run(configuration)

    @AutoCleanup
    @Shared
    SessionFactory ssf = applicationContext.getBean(SessionFactory)

    EntityManager getEntityManager() {
        ssf.openSession().entityManagerFactory.createEntityManager()
    }

    EntityManager em

    def setup() {
        em = entityManager
    }

    def cleanup() {
        clear(em)
        em.clear()
    }

}
