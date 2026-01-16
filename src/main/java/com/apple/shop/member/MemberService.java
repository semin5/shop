package com.apple.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(String username, String password, String displayName){

        Member member = new Member();
        member.setUsername(username);
        String hash = passwordEncoder.encode(password);
        member.setPassword(hash);
        member.setDisplayName(displayName);
        memberRepository.save(member);
    }

}
