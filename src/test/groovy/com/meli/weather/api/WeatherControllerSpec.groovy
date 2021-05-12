package com.meli.weather.api

import com.meli.weather.EmbeddedServerBaseSpec
import com.meli.weather.application.output.WeatherForecastOutput
import com.meli.weather.fixtures.EntityManagerFixtures
import com.meli.weather.fixtures.WeatherForecastFixtures
import io.micronaut.http.HttpMethod
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus

class WeatherControllerSpec extends EmbeddedServerBaseSpec implements EntityManagerFixtures, WeatherForecastFixtures {

    String basePath = "/weather"

    void 'should return a weather when find a forecast for the day'() {
        given: 'a forecast for the day'
        def forecast = weatherForecastModel
        persist(em, forecast)
        def request = HttpRequest.create(HttpMethod.GET, String.format("%s/%d", basePath, forecast.day))

        when: 'search for a day'
        HttpResponse<WeatherForecastOutput> response = client.exchange(request)

        then: 'the result should be ok'
        assert response.status() == HttpStatus.OK

        cleanup:
        remove(em, forecast)
    }
}
