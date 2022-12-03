package ru.gb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.data.Product;
import ru.gb.services.ProductService;

import java.util.List;

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
    public List<Product> getProducts(@RequestParam(value = "min",required = false) Double min,@RequestParam(value = "max",required = false) Double max) {
        if (null == min && null == max) {
            return productService.getProducts();
        } else {
            return productService.getProductFilter(min, max);
        }
    }

    @PostMapping
    public Product addProduct(@RequestParam String name, @RequestParam double cost) {
        return productService.addProduct(name, cost);
    }

    @GetMapping("/delete/{id}")
    public void delProduct(@PathVariable Long id) {
        productService.delProduct(id);
    }
}