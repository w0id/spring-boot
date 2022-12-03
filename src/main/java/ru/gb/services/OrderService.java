package ru.gb.services;

import org.springframework.stereotype.Component;
import ru.gb.repositories.ProductRepository;
import ru.gb.repositories.CustomerRepository;

@Component
public class OrderService {

    ProductRepository productRepository;
    CustomerRepository customerRepository;

    public void getOrderById(Long id) {
        System.out.println(customerRepository.getOrdersById(id));
    }

    public void getCustomerById(Long id) {
        System.out.println(productRepository.getCustomersById(id));
    }

    public void getAllCustomers() {
        System.out.println(customerRepository.getAllCustomers());
    }

    public void getAllProducts() {
        System.out.println(productRepository.getAllProducts());
    }

    public void findCustomerById(Long id) {
        System.out.println(customerRepository.findById(id));
    }

    public void findProductById(Long id) {
        System.out.println(productRepository.findById(id));
    }

    public OrderService(final ProductRepository productRepository, final CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }
}
