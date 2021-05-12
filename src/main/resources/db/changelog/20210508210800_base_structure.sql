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

CREATE TABLE jobs_control
(
    id             VARCHAR(36) PRIMARY KEY,
    type           VARCHAR(40) NOT NULL UNIQUE,
    status         VARCHAR(40) NOT NULL,
    execution_date TIMESTAMP   NOT NULL,
    error_message  TEXT
);

--rollback: DROP TABLE planets;DROP TABLE weather_forecasts;DROP TABLE jobs_control;