package com.apple.shop.member;

import com.apple.shop.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/register")
    String register(){

        return "register.html";
    }

    @PostMapping("/member")
    String addMember(@RequestParam String username,
                    @RequestParam String password,
                    @RequestParam String displayName){

        memberService.register(username, password, displayName);

        return "redirect:/list";
    }
}
