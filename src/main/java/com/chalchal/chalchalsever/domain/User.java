package com.chalchal.chalchalsever.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class User {
    private Long id;
    private String name;
    private String nickName;
    private String hp;

    private String pw;
    private List<String> roles;
}
