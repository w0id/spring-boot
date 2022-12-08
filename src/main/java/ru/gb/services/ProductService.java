package ru.gb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.data.Cart;
import ru.gb.data.Product;
import ru.gb.repositories.IProductRepository;
import ru.gb.repositories.specifications.ProductsSpecifications;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private Cart cart;

    public List<Product> getCartItems() {
        return cart.getCartItems();
    }

    public List<Product> addToCart(Product product) {
        return cart.addProduct(product);
    }

    public List<Product> deleteFromCart(Long id, String name, Double cost) {
        Product product = new Product(id, name, cost);
        return cart.delProduct(product);
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public Product save(Product product) {
        return productRepository.save(product);
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
}
