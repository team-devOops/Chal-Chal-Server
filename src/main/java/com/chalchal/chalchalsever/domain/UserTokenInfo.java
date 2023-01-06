package com.chalchal.chalchalsever.domain;

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
@Table(name = "T_LOGIN_TOKEN_INFO")
@NoArgsConstructor
public class UserTokenInfo extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TOKEN_INDEX", nullable = false)
    private Long tokenIndex;


    @Comment("유저 KEY")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Comment("REFRESH 토큰")
    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;
}