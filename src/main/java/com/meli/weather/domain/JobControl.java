package com.meli.weather.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class JobControl {

    private String id;

    private LocalDateTime executionDate;

    private JobTypeEnum type;

    private JobStatusEnum status;

    private String errorMessage;

    public JobControl(String id, LocalDateTime executionDate, JobTypeEnum type, JobStatusEnum status, String errorMessage) {
        this.id = id;
        this.executionDate = executionDate;
        this.type = type;
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public static JobControl start(JobTypeEnum type) {
        return new JobControl(UUID.randomUUID().toString(), LocalDateTime.now(), type, JobStatusEnum.STARTED, null);
    }

    public JobControl finish() {
        this.status = JobStatusEnum.FINISHED;
        return this;
    }

    public JobControl finishWithFailure(String message) {
        this.status = JobStatusEnum.FAILED;
        this.errorMessage = message;
        return this;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getExecutionDate() {
        return executionDate;
    }

    public JobTypeEnum getType() {
        return type;
    }

    public JobStatusEnum getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
