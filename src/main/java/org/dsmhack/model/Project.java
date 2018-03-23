/*
 * Here to Help
 * This is a restful web service used to log hours for non-profits to submit for money grants.
 *
 * OpenAPI spec version: 1.0.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package org.dsmhack.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Project
 */
@Entity
@Table(name = "project")
public class Project {
    @Id
    @Column(name = "proj_guid")
    private String projGuid;

    @Column(name = "org_guid")
    private String orgGuid;

    @Column(name = "proj_id")
    private String projId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_dt")
    private Timestamp startDate;

    @Column(name = "end_dt")
    private Timestamp endDate;

    public String getProjGuid() {
        return projGuid;
    }

    public void setProjGuid(String projGuid) {
        this.projGuid = projGuid;
    }

    public String getOrgGuid() {
        return orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Project setDescription(String description) {
        this.description = description;
        return this;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public Project setEndDate(Timestamp endDate) {
        this.endDate = endDate;
        return this;
    }
}

