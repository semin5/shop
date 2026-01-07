package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationRepository notificationRepository;

    @GetMapping("/notification")
    String notification(Model model){
        List<Notification> result = notificationRepository.findAll();
        model.addAttribute("notifications", result);

        return "notification.html";
    }
}
