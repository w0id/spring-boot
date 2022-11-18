package ru.gb.factories;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class SessionFactory {

    private org.hibernate.SessionFactory factory;

    public void init() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public Session getSession() {
        return factory.getCurrentSession();
    }

    public void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }

}
