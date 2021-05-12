package com.meli.weather.domain.repository;

import com.meli.weather.domain.JobControl;
import com.meli.weather.domain.JobTypeEnum;

public interface JobControlRepository {

    JobControl findJobByType(JobTypeEnum type);

    JobControl createJob(JobControl jobControl);

    JobControl updateJob(JobControl jobControl);

}
