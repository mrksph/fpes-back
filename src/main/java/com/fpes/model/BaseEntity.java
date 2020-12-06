package com.fpes.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

    //@Id
    //@GeneratedValue
    //private Long id;

    @Column(name = "is_active")
    private Boolean isActive;

    @LastModifiedDate
    @Column(name = "updated_by")
    private Long updatedBy;

    @LastModifiedBy
    @Column(name = "updated_at")
    private String updatedAt;

    @CreatedDate
    @Column(name = "created_at")
    private String createdAt;

    @CreatedBy
    @Column(name = "created_by")
    private Long createdBy;

}
