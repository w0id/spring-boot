package ru.gb;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.services.OrderService;


public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("ru.gb.config");

        OrderService orderService = context.getBean(OrderService.class);
        orderService.getAllProducts();
        orderService.getAllCustomers();
        orderService.findCustomerById(1L);
        orderService.getOrderById(1L);
        orderService.findProductById(1L);
        orderService.getCustomerById(1L);
    }

}
