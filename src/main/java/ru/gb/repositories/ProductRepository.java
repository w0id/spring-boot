package ru.gb.repositories;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.gb.dao.IProductDao;
import ru.gb.data.Product;
import ru.gb.factories.SessionFactory;
import ru.gb.services.ProductRepositoryNameService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component
public class ProductRepository implements IProductDao {
    private ProductRepositoryNameService productRepositoryNameService;

    SessionFactory factory = new SessionFactory();

    public ProductRepository(final ProductRepositoryNameService productRepositoryNameService) {
        this.productRepositoryNameService = productRepositoryNameService;
    }

    @PostConstruct
    public void init() {
        factory.init();
        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            productRepositoryNameService.InitName(product);
            this.addProduct(product);
        }
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
    public void deleteProduct(final Long id) {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void addProduct(Product product) {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public Product changeCost(Product product) {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
        return product;
    }

}