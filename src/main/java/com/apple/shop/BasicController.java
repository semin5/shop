package com.apple.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
public class BasicController {

    @GetMapping("/")
    String hello(){
        return "index.html";
    }

    @GetMapping("/about")
    @ResponseBody
    String about(){
        return "피싱사이트에요";
    }

    @GetMapping("/mypage")
    @ResponseBody
    String mypage(){
        return "마이페이지에요";
    }

    @GetMapping("/date")
    @ResponseBody
    LocalDateTime date(){
        return LocalDateTime.now();
    }
}
