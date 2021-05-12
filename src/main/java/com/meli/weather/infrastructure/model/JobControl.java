package com.meli.weather.infrastructure.model;

import com.meli.weather.domain.JobStatusEnum;
import com.meli.weather.domain.JobTypeEnum;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs_control")
public class JobControl {

    @Id
    private String id;

    private LocalDateTime executionDate;

    @Enumerated(EnumType.STRING)
    private JobTypeEnum type;

    @Enumerated(EnumType.STRING)
    private JobStatusEnum status;

    private String errorMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDateTime executionDate) {
        this.executionDate = executionDate;
    }

    public JobTypeEnum getType() {
        return type;
    }

    public void setType(JobTypeEnum type) {
        this.type = type;
    }

    public JobStatusEnum getStatus() {
        return status;
    }

    public void setStatus(JobStatusEnum status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
