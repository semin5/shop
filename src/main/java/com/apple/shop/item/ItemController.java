package com.apple.shop.item;

import com.apple.shop.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final S3Service s3Service;


    @GetMapping("/list")
    String list(Model model) {

        model.addAttribute("items", itemService.list());

        return "redirect:/list/page/1";
    }

    @GetMapping("/write")
    String write(Model model) {

        return "write.html";
    }

    @PostMapping("/add")
    String addPost(@RequestParam String title,
                   @RequestParam Integer price,
                   @RequestParam String filename) {

        itemService.saveItem(title, price, filename);

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

    @GetMapping("/list/page/{pages}")
    String getListPage(Model model, @PathVariable Integer pages) {

        Page<Item> result = itemService.getListPage(pages);

        model.addAttribute("items", result);
        model.addAttribute("pages", result.getTotalPages());

        return "list.html";
    }

    @GetMapping("/presigned-url")
    @ResponseBody
    String getURL(@RequestParam String filename) {

        var result = s3Service.createPresignedUrl("test/" + filename);

        return result;
    }

}
