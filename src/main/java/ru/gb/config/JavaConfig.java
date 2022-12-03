package ru.gb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.gb.repositories.ProductRepository;
import ru.gb.repositories.CustomerRepository;
import ru.gb.services.OrderService;

@Configuration
public class JavaConfig {

    @Bean
    public OrderService orderService(ProductRepository productRepository, CustomerRepository customerRepository) {
        return new OrderService(productRepository, customerRepository);
    }

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepository();
    }

    @Bean
    public CustomerRepository customerRepository() {
        return new CustomerRepository();
    }
}
