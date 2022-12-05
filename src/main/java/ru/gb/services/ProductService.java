package ru.gb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.data.Product;
import ru.gb.repositories.IProductRepository;

import java.util.List;
@Service
public class ProductService {

    @Autowired
    private IProductRepository productRepository;

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public Product addProduct(String name, double cost) {
        Product product = new Product(name, cost);
        productRepository.save(product);
        return null;
    }

    public void delProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductFilter(Double min, Double max) {
        return productRepository.findAllByCostBetween(min, max);
    }
}
