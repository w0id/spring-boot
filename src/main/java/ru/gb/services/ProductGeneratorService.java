package ru.gb.services;

import org.springframework.stereotype.Service;
import ru.gb.data.Product;
import java.util.Random;

@Service
public class ProductGeneratorService {
    private static long productSequence = 1L;

    public void InitProduct(Product product) {
        Random random = new Random();
        product.setName("Товар №" + random.nextInt(99));
        product.setCost(Math.round(100000d * random.nextDouble()) / 100d);
    }
}