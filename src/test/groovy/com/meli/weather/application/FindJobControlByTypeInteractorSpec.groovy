package com.meli.weather.application

import com.meli.weather.ApplicationContextBaseSpec
import com.meli.weather.domain.JobTypeEnum
import com.meli.weather.fixtures.JobControlFixtures
import spock.lang.Subject

class FindJobControlByTypeInteractorSpec extends ApplicationContextBaseSpec implements JobControlFixtures {

    @Subject
    FindJobControlByTypeInteractor findJobControlByTypeInteractor = applicationContext.getBean(FindJobControlByTypeInteractor)

    void 'should find a job by type'() {
        given: 'a already existent job'
        def jobModel = jobControlModel
        persist(em, jobModel)

        when: 'looks for a job'
        def foundJob = findJobControlByTypeInteractor.execute(JobTypeEnum.FORECAST)

        then: 'the job should be created'
        noExceptionThrown()
        assert foundJob.id == jobModel.id
        assert foundJob.type == jobModel.type
        assert foundJob.executionDate == jobModel.executionDate
        assert foundJob.errorMessage == jobModel.errorMessage
        assert foundJob.status == jobModel.status

        cleanup:
        remove(em, jobModel)
    }

    void 'should return null when no job found'() {
        when: 'looks for a job'
        def foundJob = findJobControlByTypeInteractor.execute(JobTypeEnum.FORECAST)

        then: 'should return null'
        noExceptionThrown()
        assert foundJob == null
    }
}
