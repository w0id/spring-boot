package ru.gb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.data.Product;
import ru.gb.repositories.IProductRepository;
import ru.gb.repositories.specifications.ProductsSpecifications;

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

    public Page<Product> getProductFilter(Double min, Double max, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (null != min) {
            spec = spec.and(ProductsSpecifications.costGreaterThenOrEqualsThen(min));
        }
        if (null != max) {
            spec = spec.and(ProductsSpecifications.costLessThenOrEqualsThen(max));
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 10, Sort.by("id").ascending()));
    }

    public void changeCost(final Long productId, final Integer delta) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.setCost(product.getCost() + delta);
        productRepository.save(product);
    }
}
