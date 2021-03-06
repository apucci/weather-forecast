package com.meli.weather.application;

import com.meli.weather.domain.JobControl;
import com.meli.weather.domain.JobTypeEnum;

public interface FindJobControlByTypeInteractor {
    JobControl execute(JobTypeEnum type);
}
