package ru.gb.data;

public class Product {

    private Long id;
    private String name;
    private Double cost;

    public Product() {
    }

    public Product(final Long id, final String name, final Double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getCost() {
        return cost;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setCost(final Double cost) {
        this.cost = cost;
    }
}

