package com.meli.weather

import com.meli.weather.fixtures.ConfigurationFixtures
import com.meli.weather.fixtures.EntityManagerFixtures
import io.micronaut.context.ApplicationContext
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.hibernate.SessionFactory
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import javax.persistence.EntityManager

abstract class EmbeddedServerBaseSpec extends Specification implements ConfigurationFixtures, EntityManagerFixtures {

    @AutoCleanup
    @Shared
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, configuration)

    @AutoCleanup
    @Shared
    ApplicationContext applicationContext = embeddedServer.applicationContext

    @AutoCleanup
    @Shared
    SessionFactory ssf = applicationContext.getBean(SessionFactory)

    @AutoCleanup
    @Shared
    HttpClient localHttpClient = applicationContext.createBean(HttpClient, embeddedServer.URL)

    EntityManager em

    BlockingHttpClient getClient() {
        localHttpClient.toBlocking()
    }

    EntityManager getEntityManager() {
        ssf.openSession().entityManagerFactory.createEntityManager()
    }

    def setup() {
        em = entityManager
    }

    def cleanup() {
        clear(em)
        em.clear()
    }

}
