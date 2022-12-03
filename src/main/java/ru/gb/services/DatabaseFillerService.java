package ru.gb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.gb.data.Product;
import ru.gb.repositories.IProductRepository;

@Service
public class DatabaseFillerService {

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ProductGeneratorService productGeneratorService;

    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabaseOnStartup() {
        for (int i = 0; i < 20; i++) {
            Product product = new Product();
            productGeneratorService.InitProduct(product);
            productRepository.save(product);
        }
    }

}
