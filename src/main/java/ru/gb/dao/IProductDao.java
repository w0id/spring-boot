package ru.gb.dao;

import org.springframework.stereotype.Component;
import ru.gb.data.Customer;
import ru.gb.data.Product;

import java.util.List;
@Component
public interface IProductDao {
    List<Product> getAllProducts();
    Product findById(Long id);
    List<Customer> getCustomersById(Long id);
}
