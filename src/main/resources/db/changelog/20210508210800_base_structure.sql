--liquibase formatted sql
--changeset author:apucci
CREATE TABLE planets
(
    id       VARCHAR(36) PRIMARY KEY,
    name     VARCHAR(40) NOT NULL,
    speed    INTEGER     NOT NULL,
    distance INTEGER     NOT NULL
);

CREATE TABLE weather_forecasts
(
    day            INTEGER PRIMARY KEY,
    weather        VARCHAR(40) NOT NULL,
    rain_intensity DECIMAL
);

--rollback: DROP TABLE PLANETS;DROP TABLE WEATHER_FORECAST;