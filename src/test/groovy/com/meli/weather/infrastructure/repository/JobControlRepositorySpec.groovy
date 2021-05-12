package com.meli.weather.infrastructure.repository

import com.meli.weather.ApplicationContextBaseSpec
import com.meli.weather.domain.JobStatusEnum
import com.meli.weather.domain.JobTypeEnum
import com.meli.weather.domain.repository.JobControlRepository
import com.meli.weather.fixtures.JobControlFixtures
import spock.lang.Subject

class JobControlRepositorySpec extends ApplicationContextBaseSpec implements JobControlFixtures {

    @Subject
    JobControlRepository jobControlRepository = applicationContext.getBean(JobControlRepository)

    void 'should create a job'() {
        given: 'a new jobControl'
        def job = startJobControlDomain(JobTypeEnum.FORECAST)

        when: 'saves the job'
        def createdJob = jobControlRepository.createJob(job)

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

    void 'should update a job'() {
        given: 'a already existent job'
        def jobModel = jobControlModel
        persist(em, jobModel)

        jobModel.status = JobStatusEnum.FINISHED

        when: 'updates the job'
        def updatedJob = jobControlRepository.updateJob(jobDomainFromModel(jobModel))

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

    void 'should find a job by type'() {
        given: 'a already existent job'
        def jobModel = jobControlModel
        persist(em, jobModel)

        when: 'looks for a job'
        def foundJob = jobControlRepository.findJobByType(JobTypeEnum.FORECAST)

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
        def foundJob = jobControlRepository.findJobByType(JobTypeEnum.FORECAST)

        then: 'should return null'
        noExceptionThrown()
        assert foundJob == null
    }
}
