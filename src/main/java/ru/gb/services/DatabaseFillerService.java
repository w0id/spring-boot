package ru.gb.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.gb.data.Product;
import ru.gb.repositories.IProductRepository;

@Service
@RequiredArgsConstructor
public class DatabaseFillerService {

    private IProductRepository productRepository;
    private ProductGeneratorService productGeneratorService;

//    @EventListener(ApplicationReadyEvent.class)
//    public void fillDatabaseOnStartup() {
//        for (int i = 0; i < 100; i++) {
//            Product product = new Product();
//            productGeneratorService.InitProduct(product);
//            productRepository.save(product);
//        }
//    }
}
