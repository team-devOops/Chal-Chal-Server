package com.chalchal.chalchalsever.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseDomain {
    @CreatedDate
    @Column(name = "reg_date", nullable = false, updatable = false)
    private Date regDate;

    @CreatedBy
    @Column(name = "reg_id", nullable = false, updatable = false)
    private String regId;

    @LastModifiedDate
    @Column(name = "upd_date")
    private Date updDate;

    @LastModifiedBy
    @Column(name = "upd_id")
    private String updId;
}
