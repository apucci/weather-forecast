package com.meli.weather.api.exceptionhandler;

import com.meli.weather.api.representation.ErrorResponse;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {Exception.class, ExceptionHandler.class})
public class ServerErrorExceptionHandler implements ExceptionHandler<Exception, HttpResponse> {

    private Logger LOGGER = LoggerFactory.getLogger(ServerErrorExceptionHandler.class);

    @Override
    public HttpResponse handle(HttpRequest request, Exception exception) {
        LOGGER.error(String.format("Server Error: %s", exception.getMessage()), exception);
        return HttpResponse.serverError(new ErrorResponse("We had some unknown error, contact our team and try again later"));
    }
}
