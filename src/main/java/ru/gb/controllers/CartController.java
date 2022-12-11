package ru.gb.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.data.Product;
import ru.gb.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart_items")
@AllArgsConstructor
public class CartController {
    private ProductService productService;

    @GetMapping
    public List<Product> getCartItems() {
        return productService.getCartItems();
    }

    @PostMapping
    public List<Product> addToCart(@RequestBody Product product) {
        return productService.addToCart(product);
    }

    @DeleteMapping
    public List<Product> deleteFromCart(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam Double cost
            ) {
        return productService.deleteFromCart(id, name, cost);
    }
}