package com.apple.shop.member;

import lombok.RequiredArgsConstructor;
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
                    @RequestParam String displayName) throws Exception {

        memberService.saveMember(username, password, displayName);

        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }


}
