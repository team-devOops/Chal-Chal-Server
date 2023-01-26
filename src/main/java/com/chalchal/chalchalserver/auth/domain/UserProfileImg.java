package com.chalchal.chalchalserver.auth.domain;

import com.chalchal.chalchalserver.global.BaseDomain;
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
@Table(name = "T_USER_PROFILE_IMG")
@NoArgsConstructor
public class UserProfileImg extends BaseDomain {
    @Id
    @Comment("서비스 요청 번호")
    @Column(name = "SVC_NO", nullable = false)
    private String svcNo;

    @Comment("유저 KEY")
    @Column(name = "ID", nullable = false, columnDefinition = "bigint")
    private Long id;

    @Comment("정렬 시퀀스")
    @Column(name = "ORDER_SEQ", nullable = false, columnDefinition = "int")
    private int orderSeq;

    @Comment("이미지 URL")
    @Column(name = "URL", nullable = false, columnDefinition = "varchar(256)")
    private String url;
}
