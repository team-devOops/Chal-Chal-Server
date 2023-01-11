package com.chalchal.chalchalsever.domain.auth.entity;

import com.chalchal.chalchalsever.domain.BaseDomain;
import com.chalchal.chalchalsever.global.util.DateUtils;
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
    @Column(name = "SVC_NO", nullable = false, columnDefinition = "varchar(32)")
    private String reqSvcNo;

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
    @Comment("인증유효날짜(YYYYMMDD)")
    @Column(name = "VALID_DATE", nullable = false, columnDefinition = "char(8)")
    private String validDate = applyValidDate();

    @Builder.Default
    @Comment("인증유효시간(HHmmss)")
    @Column(name = "VALID_TIME", nullable = false, columnDefinition = "char(6)")
    private String validTime = applyValidTime();

    @Comment("인증여부")
    @Column(name = "AUTH_YN", nullable = false, columnDefinition = "char(1)")
    private String authYn;

    /**
     * 인증 유효 기간 설정
     */
    //TODO: 230111 하단 로직 고민 더 해보기
    public static String applyValidDate() {
        String date = getValidity();
        return DateUtils.getDateByPattern(date, DateUtils.DATE_PATTERN_FORMAT);
    }

    public static String applyValidTime() {
        String date = getValidity();
        return DateUtils.getDateByPattern(date, DateUtils.DATE_TIME_HMS_FORMAT);
    }

    private static String getValidity() {
        return DateUtils.plusMinuteByCurrentDay(10);
    }
}