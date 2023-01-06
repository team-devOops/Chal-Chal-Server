package com.chalchal.chalchalsever.domain.auth.entity;

import com.chalchal.chalchalsever.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@Table(name = "T_USER_JOIN_AUTH")
@NoArgsConstructor
public class UserJoinAuth extends BaseDomain {

    @Id
    @Comment("서비스번호")
    @Column(name = "SVC_NO")
    private String reqSvcNo;

    @Comment("유저 KEY")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Comment("받는 이메일")
    @Column(name = "SEND_EMAIL", nullable = false)
    private String sendEmail;

    @Comment("인증코드")
    @Column(name = "AUTH_CODE", nullable = false)
    private String authCode;

    @Comment("인증유효날짜")
    @Column(name = "VALID_DATE", nullable = false)
    private String validDate;

    @Comment("인증유효시간")
    @Column(name = "VALID_TIME", nullable = false)
    private String validTime;

    @Comment("인증여부")
    @Column(name = "AUTH_YN", nullable = false)
    private String authYn;
}