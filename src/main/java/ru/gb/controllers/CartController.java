package ru.gb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.data.Product;
import ru.gb.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart_items")
public class CartController {
    @Autowired
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