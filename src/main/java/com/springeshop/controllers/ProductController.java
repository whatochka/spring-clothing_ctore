package com.springeshop.controllers;

import com.springeshop.data.dto.ProductDTO;
import com.springeshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String productList(Model model) {
        List<ProductDTO> productDTOs = productService.getAll();
        List<String> categoriesNames = productService.getAllCategories();
        model.addAttribute("products", productDTOs);
        model.addAttribute("categories", categoriesNames);
        return "product-list";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new ProductDTO());
        return "product";
    }

    @PostMapping("/new")
    public String saveProduct(ProductDTO productDTO) {
        productService.save(productDTO);
        return "redirect:/products";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam("searchTerm") String searchTerm, Model model) {
        List<ProductDTO> products = productService.searchProducts(searchTerm);
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/{category}")
    public String filterProductsOnCategory(@PathVariable String category, Model model){
        List<ProductDTO> products = productService.filterProductOnCategory(category);
        model.addAttribute("products", products);
        return "product-list";
    }
}
