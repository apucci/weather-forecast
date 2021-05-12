package com.meli.weather.application

import com.meli.weather.ApplicationContextBaseSpec
import com.meli.weather.domain.JobTypeEnum
import com.meli.weather.fixtures.JobControlFixtures
import spock.lang.Subject

class CreateJobControlInteractorSpec extends ApplicationContextBaseSpec implements JobControlFixtures {

    @Subject
    CreateJobControlInteractor createJobControlInteractor = applicationContext.getBean(CreateJobControlInteractor)

    void 'should create a job'() {
        given: 'a new jobControl'
        def job = startJobControlDomain(JobTypeEnum.FORECAST)

        when: 'saves the job'
        def createdJob = createJobControlInteractor.execute(job)

        then: 'the job should be created'
        noExceptionThrown()
        assert createdJob.id == job.id
        assert createdJob.type == job.type
        assert createdJob.status == job.status
        assert createdJob.executionDate == job.executionDate
        assert createdJob.errorMessage == job.errorMessage

        cleanup:
        remove(em, getJobControlModelWithId(job.id))
    }
}
