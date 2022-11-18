package ru.gb.services;

import org.springframework.stereotype.Service;
import ru.gb.data.Product;
import ru.gb.repositories.ProductRepository;

import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public void deleteProduct(final Long id) {
        productRepository.deleteProduct(id);
    }

    public void changeCost(final Long productId, final Integer delta) {
        Product product = productRepository.findById(productId);
        productRepository.changeCost(productId, product.getCost() + delta);
    }

    public void addProduct(final String name, final double cost) {
        Product product = new Product(null, name, cost);
        productRepository.addProduct(product);
    }
}

