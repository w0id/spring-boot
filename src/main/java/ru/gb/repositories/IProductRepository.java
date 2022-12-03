package ru.gb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.data.Product;

import java.io.Serializable;
import java.util.List;
@Repository
public interface IProductRepository extends JpaRepository<Product, Serializable> {
    public List<Product> findAllByCostBetween(Double min, Double max);
}
