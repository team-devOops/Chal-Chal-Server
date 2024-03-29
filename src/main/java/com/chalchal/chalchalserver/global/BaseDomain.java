package com.chalchal.chalchalserver.global;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseDomain {

    @CreatedBy
    @Column(name = "reg_id", nullable = false, updatable = false)
    private Long regId;

    @CreatedDate
    @Column(name = "reg_date", nullable = false, updatable = false)
    private LocalDateTime regDate;

    @LastModifiedBy
    @Column(name = "upd_id")
    private Long updId;

    @LastModifiedDate
    @Column(name = "upd_date")
    private LocalDateTime updDate;
}
