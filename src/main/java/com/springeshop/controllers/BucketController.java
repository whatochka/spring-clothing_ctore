package com.springeshop.controllers;

import com.springeshop.data.domain.PurchasedItem;
import com.springeshop.service.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/bucket")
@RequiredArgsConstructor
public class BucketController {

    private final BucketService bucketService;

    @PostMapping("/{productId}")
    public String addItemToBucket(@PathVariable Long productId, Principal principal) {
        if (principal == null) {
            return "login";
        }
        String username = principal.getName();
        bucketService.addItemToBucket(productId, username);
        return "redirect:/products";
    }

    @PostMapping("/delete/{itemId}")
    public String removeItemFromBucket(@PathVariable Long itemId) {
        bucketService.removeItemFromBucket(itemId);
        return "redirect:/bucket";
    }

    @GetMapping
    public String getBucketItems(Model model, Principal principal) {
        if (principal == null) {
            return "login";
        }
        List<PurchasedItem> BucketProducts = bucketService.getAll(principal.getName());
        model.addAttribute("bucket", BucketProducts);
        return "bucket-list";
    }
}
