package ru.gb.controllers;

import org.springframework.web.bind.annotation.*;
import ru.gb.data.Product;
import ru.gb.services.ProductService;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/products/change_cost")
    public void changeCost(Long productId, Integer delta) {
        productService.changeCost(productId, delta);
    }


    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/products/add_product")
    public void addProduct(@RequestParam String name, @RequestParam double cost) {
        productService.addProduct(name, cost);
    }

}