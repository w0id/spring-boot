package ru.gb.dao;

import org.springframework.stereotype.Component;
import ru.gb.data.Product;

import java.util.List;
@Component
public interface IProductDao {
    List<Product> getAllProducts();
    Product findById(Long id);
    void deleteProduct(final Long id);
    void addProduct(Product product);

    void changeCost(Long id, double v);
}
