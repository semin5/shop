package com.apple.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/list")
    String list(Model model) {

        model.addAttribute("items", itemService.list());

        return "list.html";
    }

    @GetMapping("/write")
    String write(Model model) {

        return "write.html";
    }

    @PostMapping("/add")
    String addPost(@RequestParam String title,
                   @RequestParam Integer price) {

        itemService.saveItem(title, price);

        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model) {

        Optional<Item> result = itemService.detail(id);

        if (result.isPresent()) {
            model.addAttribute("data", result.get());

            return "detail.html";
        } else {

            return "redirect:/list";
        }
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable Long id, Model model) {

        Optional<Item> result = itemService.detail(id);

        if (result.isPresent()) {
            model.addAttribute("data", result.get());

            return "edit.html";
        }else {

            return "redirect:/list";
        }
    }

    @PostMapping("/edit")
    String editItem(@RequestParam String title,
                   @RequestParam Integer price,
                    @RequestParam Long id) {

        itemService.editItem(id, title, price);

        return "redirect:/list";
    }

    @DeleteMapping("/item")
    ResponseEntity<String> deleteItem(@RequestParam Long id){
        itemService.deleteItem(id);
        return ResponseEntity.status(200).body("삭제");
    }

    @GetMapping("/test2")
    String password(){
        var result = new BCryptPasswordEncoder().encode("문자~~~");
        System.out.println(result);
        return "redirect:/list";

    }
}
