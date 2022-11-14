package ru.gb.repositories;

import org.springframework.stereotype.Component;
import ru.gb.data.Product;
import ru.gb.services.ProductRepositoryNameService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class ProductRepository {
    private ProductRepositoryNameService productRepositoryNameService;

    public ProductRepository(final ProductRepositoryNameService productRepositoryNameService) {
        this.productRepositoryNameService = productRepositoryNameService;
    }
    private static List<Product> products = new ArrayList<>();

    @PostConstruct
    public void init() {
        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            productRepositoryNameService.InitName(product);
            products.add(product);
        }
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public void deleteProduct(final Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("Товар не найден"));
    }

    public void addProduct(final Long id, final String name, final double cost) {
        products.add(new Product(id, name, cost));
    }
}