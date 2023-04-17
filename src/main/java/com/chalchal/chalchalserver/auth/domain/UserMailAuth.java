package com.chalchal.chalchalserver.auth.domain;

import com.chalchal.chalchalserver.global.BaseDomain;
import com.chalchal.chalchalserver.global.dto.Flag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Getter
@Entity
@AllArgsConstructor
@Table(name = "USER_MAIL_AUTH")
@NoArgsConstructor
public class UserMailAuth extends BaseDomain {

    public static final int AUTH_VALID_MAX_TIME = 10;
    @Id
    @Comment("서비스번호")
    @Column(name = "SVC_NO", nullable = false, columnDefinition = "varchar(32)")
    private String svcNo;

    @Comment("유저 KEY")
    @Column(name = "ID", nullable = false, columnDefinition = "bigint")
    private Long id;

    @Comment("받는 이메일")
    @Column(name = "SEND_EMAIL", nullable = false, columnDefinition = "varchar(128)")
    private String sendEmail;

    @Comment("인증코드")
    @Column(name = "AUTH_CODE", nullable = false, columnDefinition = "char(6)")
    private String authCode;

    @Builder.Default
    @Comment("인증유효시간")
    @Column(name = "VALID_DATE", nullable = false, columnDefinition = "datetime")
    private LocalDateTime validDate = setValidDate();

    @Comment("인증여부")
    @Enumerated(EnumType.STRING)
    @Column(name = "AUTH_YN", nullable = false, columnDefinition = "char(1)")
    private Flag authYn;

    @Comment("인증성공일자")
    @Column(name = "SUCCESS_AUTH_DATE", columnDefinition = "datetime")
    private LocalDateTime successAuthDate;

    public void successAuth() {
        this.authYn = Flag.Y;
        this.successAuthDate = LocalDateTime.now();
    }

    public static LocalDateTime setValidDate() {
        return LocalDateTime.now().plusMinutes(AUTH_VALID_MAX_TIME);
    }

    public boolean isEqualAuthCode(String authCode) {
        return Objects.equals(this.authCode, authCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authCode);
    }

    @Override
    public String toString() {
        return "UserJoinAuth{" +
                "reqSvcNo='" + svcNo + '\'' +
                ", id=" + id +
                ", sendEmail='" + sendEmail + '\'' +
                ", authCode='" + authCode + '\'' +
                ", validDate='" + validDate + '\'' +
                ", authYn='" + authYn + '\'' +
                '}';
    }
}