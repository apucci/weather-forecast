package com.meli.weather.infrastructure.service

import com.meli.weather.domain.JobControl
import com.meli.weather.domain.JobTypeEnum
import com.meli.weather.fixtures.JobControlFixtures
import spock.lang.Specification
import spock.lang.Subject

class JobControlServiceSpec extends Specification implements JobControlFixtures {

    @Subject
    private JobControlService jobControlService = new JobControlService()

    void 'Should create a jobControl domain object when passes a jobControl model object'() {
        given: 'a jobControl model'
        def jobModel = jobControlModel

        when: 'converting it to a domain object'
        def result = jobControlService.fromModelToDomain(jobModel)

        then: 'the attributes must maintain the same'
        noExceptionThrown()
        assert result as JobControl
        assert result.id == jobModel.id
        assert result.executionDate == jobModel.executionDate
        assert result.type == jobModel.type
        assert result.status == jobModel.status
        assert result.errorMessage == jobModel.errorMessage
    }

    void 'Should create a jobControl model object when passes a jobControl domain object'() {
        given: 'a jobControl model'
        def jobDomain = startJobControlDomain(JobTypeEnum.FORECAST)

        when: 'converting it to a domain object'
        def result = jobControlService.fromDomainToModel(jobDomain)

        then: 'the attributes must maintain the same'
        noExceptionThrown()
        assert result as com.meli.weather.infrastructure.model.JobControl
        assert result.id == jobDomain.id
        assert result.executionDate == jobDomain.executionDate
        assert result.type == jobDomain.type
        assert result.status == jobDomain.status
        assert result.errorMessage == jobDomain.errorMessage
    }
}
