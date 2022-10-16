package com.chalchal.chalchalsever.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@Table(name = "t_user_auth")
@NoArgsConstructor
@DynamicUpdate
public class UserAuth extends BaseDomain {

    @Id
    @Column(name = "req_svc_no")
    private String reqSvcNo;

    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "limit_date", nullable = false)
    private String limitDate;

    @Column(name = "limit_time", nullable = false)
    private String limitTime;

    @Column(name = "auth_yn", nullable = false)
    private String authYn;
}