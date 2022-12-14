package ru.gb.repositories;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.gb.dao.IProductDao;
import ru.gb.data.Customer;
import ru.gb.data.Product;
import ru.gb.factories.SessionFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component
public class ProductRepository implements IProductDao {

    SessionFactory factory = new SessionFactory();


    public ProductRepository() {
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
    public List<Product> getAllProducts() {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("select p from Product p order by p.id").getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    @Override
    public Product findById(final Long id) {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public List<Customer> getCustomersById(final Long id) {
        try(Session session = factory.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            product.getCustomers().toString();
            session.getTransaction().commit();
            return product.getCustomers();
        }
    }

}