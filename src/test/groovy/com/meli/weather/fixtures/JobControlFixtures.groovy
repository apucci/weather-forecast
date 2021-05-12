package com.meli.weather.fixtures

import com.meli.weather.domain.JobControl
import com.meli.weather.domain.JobStatusEnum
import com.meli.weather.domain.JobTypeEnum

import java.time.LocalDateTime

trait JobControlFixtures {

    JobControl startJobControlDomain(JobTypeEnum type) {
        new JobControl(UUID.randomUUID().toString(), LocalDateTime.now(), type, JobStatusEnum.STARTED, null)
    }

    JobControl jobDomainFromModel(com.meli.weather.infrastructure.model.JobControl jobModel) {
        new JobControl(jobModel.id, jobModel.executionDate, jobModel.type, jobModel.status, jobModel.errorMessage)
    }

    com.meli.weather.infrastructure.model.JobControl getJobControlModel() {
        def jobControlModel = new com.meli.weather.infrastructure.model.JobControl()
        jobControlModel.id = UUID.randomUUID().toString()
        jobControlModel.errorMessage = null
        jobControlModel.status = JobStatusEnum.STARTED
        jobControlModel.type = JobTypeEnum.FORECAST
        jobControlModel.executionDate = LocalDateTime.now()

        jobControlModel
    }

    com.meli.weather.infrastructure.model.JobControl getJobControlModelWithId(String id) {
        def jobControlModel = new com.meli.weather.infrastructure.model.JobControl()
        jobControlModel.id = id

        jobControlModel
    }

}
