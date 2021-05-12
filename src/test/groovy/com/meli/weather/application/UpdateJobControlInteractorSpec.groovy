package com.meli.weather.application

import com.meli.weather.ApplicationContextBaseSpec
import com.meli.weather.domain.JobStatusEnum
import com.meli.weather.fixtures.JobControlFixtures
import spock.lang.Subject

class UpdateJobControlInteractorSpec extends ApplicationContextBaseSpec implements JobControlFixtures {

    @Subject
    UpdateJobControlInteractor updateJobControlInteractor = applicationContext.getBean(UpdateJobControlInteractor)

    void 'should update a job'() {
        given: 'a already existent job'
        def jobModel = jobControlModel
        persist(em, jobModel)

        jobModel.status = JobStatusEnum.FINISHED

        when: 'updates the job'
        def updatedJob = updateJobControlInteractor.execute(jobDomainFromModel(jobModel))

        then: 'the job should be created'
        noExceptionThrown()
        assert updatedJob.id == jobModel.id
        assert updatedJob.type == jobModel.type
        assert updatedJob.executionDate == jobModel.executionDate
        assert updatedJob.errorMessage == jobModel.errorMessage
        assert updatedJob.status == JobStatusEnum.FINISHED

        cleanup:
        remove(em, jobModel)
    }
}
