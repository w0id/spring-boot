package ru.gb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.data.Product;
import ru.gb.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @GetMapping
    public Page<Product> getProducts(
            @RequestParam(value = "min",required = false) Double min,
            @RequestParam(value = "max",required = false) Double max,
            @RequestParam(value = "p", defaultValue = "1") Integer page
            ) {
        if (page < 1) {
            page = 1;
        }
        return productService.getProductFilter(min, max, page);
    }

    @PostMapping
    public Product addProduct(@RequestParam String name, @RequestParam double cost) {
        return productService.addProduct(name, cost);
    }

    @GetMapping("/delete/{id}")
    public void delProduct(@PathVariable Long id) {
        productService.delProduct(id);
    }

    @PostMapping("/change_cost")
    public void changeCost(@RequestParam Long productId, @RequestParam Integer delta) {
        productService.changeCost(productId, delta);
    }
}