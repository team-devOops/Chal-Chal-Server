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
@Table(name = "t_token_info")
@NoArgsConstructor
public class UserTokenInfo extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_index", nullable = false)
    private Long tokenIndex;

    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "refresh_token")
    private String refreshToken;
}