package ru.gb.dao;

import ru.gb.data.Customer;
import ru.gb.data.Product;

import java.util.List;

public interface ICustomerDao {
    List<Customer> getAllCustomers();
    Customer findById(Long id);
    List<Product> getOrdersById(Long id);
}
