package com.meli.weather.domain.repository;

import com.meli.weather.domain.Planet;

import java.util.List;

public interface PlanetRepository {

    List<Planet> getOriginalPlanets();

}
