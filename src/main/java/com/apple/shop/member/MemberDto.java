package com.apple.shop.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto{

    private String username;
    private String displayName;
    private Long id;

    MemberDto(String a, String b){
        this.username = a;
        this.displayName = b;
    }

    MemberDto(String a, String b, Long c){
        this.username = a;
        this.displayName = b;
        this.id = c;
    }
}
