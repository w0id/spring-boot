package ru.gb.repositories;

import org.hibernate.Session;
import ru.gb.dao.ICustomerDao;
import ru.gb.data.Customer;
import ru.gb.data.Product;
import ru.gb.factories.SessionFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

public class CustomerRepository implements ICustomerDao {

    SessionFactory factory = new SessionFactory();

    public CustomerRepository() {
    }

    @PostConstruct
    public void init() {
        factory.init();
    }

    @PreDestroy
    public void shutdown() {
        factory.shutdown();
    }

    @Override
    public List<Customer> getAllCustomers() {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            List<Customer> customers = session.createQuery("select u from Customer u order by u.id").getResultList();
            session.getTransaction().commit();
            return customers;
        }
    }

    @Override
    public Customer findById(final Long id) {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            session.getTransaction().commit();
            return customer;
        }
    }

    @Override
    public List<Product> getOrdersById(final Long id) {
        try(Session session = factory.getSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            customer.getProducts().toString();
            session.getTransaction().commit();
            return customer.getProducts();
        }
    }
}