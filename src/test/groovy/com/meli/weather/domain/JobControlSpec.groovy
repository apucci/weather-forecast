package com.meli.weather.domain

import com.meli.weather.fixtures.JobControlFixtures
import spock.lang.Specification

class JobControlSpec extends Specification implements JobControlFixtures {

    void 'Should start a new job'() {
        given: 'the type'
        def type = JobTypeEnum.FORECAST

        when: 'starts a new Job'
        def job = JobControl.start(type)

        then: 'the job should be created'
        noExceptionThrown()
        assert job.id != null
        assert job.type == type
        assert job.errorMessage == null
        assert job.status == JobStatusEnum.STARTED
        assert job.executionDate != null
    }

    void 'Should finish a job'() {
        given: 'the type'
        def job = startJobControlDomain(JobTypeEnum.FORECAST)
        def initialType = job.type
        def initialDate = job.executionDate
        def initialMessage = job.errorMessage
        def initialId = job.id

        when: 'starts a new Job'
        def finishedJob = job.finish()

        then: 'the job should be created'
        noExceptionThrown()
        assert finishedJob != null
        assert job.id == initialId
        assert job.type == initialType
        assert job.errorMessage == initialMessage
        assert job.status == JobStatusEnum.FINISHED
        assert job.executionDate == initialDate
    }

    void 'Should finish a job with failure'() {
        given: 'the type'
        def job = startJobControlDomain(JobTypeEnum.FORECAST)
        def initialType = job.type
        def initialDate = job.executionDate
        def initialId = job.id

        when: 'starts a new Job'
        def finishedJob = job.finishWithFailure("some error")

        then: 'the job should be created'
        noExceptionThrown()
        assert finishedJob != null
        assert job.id == initialId
        assert job.type == initialType
        assert job.errorMessage == "some error"
        assert job.status == JobStatusEnum.FAILED
        assert job.executionDate == initialDate
    }

}
