package ru.gb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.data.Product;
import ru.gb.dto.ProductDto;
import ru.gb.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return new ProductDto(productService.getProduct(id));
    }

    @GetMapping
    public Page<ProductDto> getProducts(
            @RequestParam(value = "min",required = false) Double min,
            @RequestParam(value = "max",required = false) Double max,
            @RequestParam(value = "p", defaultValue = "1") Integer page
            ) {
        if (page < 1) {
            page = 1;
        }
        return productService.getProductFilter(min, max, page).map(
                ProductDto::new
        );
    }

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        productService.save(new Product(productDto.getName(), productDto.getCost()));
        return productDto;
    }

    @DeleteMapping("/{id}")
    public void delProduct(@PathVariable Long id) {
        productService.delProduct(id);
    }

    @PutMapping
    public ProductDto changeCost(@RequestBody ProductDto productDto) {
        Product product = productService.getProduct(productDto.getId());
        product.setCost(productDto.getCost());
        productService.save(product);
        return productDto;
    }
}