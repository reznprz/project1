package com.Spring_crud.Curd.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)

//to print the log
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)

public abstract class AuditModel implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    //date will insert automatically
    @Column(name="craeted_At", nullable = false, updatable = false)
    @CreatedDate

    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_At", nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
