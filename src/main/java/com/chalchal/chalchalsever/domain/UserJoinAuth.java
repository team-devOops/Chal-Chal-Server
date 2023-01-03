package com.chalchal.chalchalsever.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@Table(name = "t_user_join_auth")
@NoArgsConstructor
public class UserJoinAuth extends BaseDomain {

    @Id
    @Column(name = "req_svc_no")
    private String reqSvcNo;

    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "send_email", nullable = false)
    private String sendEmail;

    @Column(name = "auth_code", nullable = false)
    private String authCode;

    @Column(name = "valid_date", nullable = false)
    private String validDate;

    @Column(name = "valid_time", nullable = false)
    private String validTime;

    @Column(name = "auth_yn", nullable = false)
    private String authYn;
}