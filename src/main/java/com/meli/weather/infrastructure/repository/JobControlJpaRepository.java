package com.meli.weather.infrastructure.repository;

import com.meli.weather.domain.JobTypeEnum;
import com.meli.weather.infrastructure.model.JobControl;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
abstract class JobControlJpaRepository implements CrudRepository<JobControl, String> {

    abstract Optional<JobControl> findByType(JobTypeEnum type);

}
