package co.unicauca.products.cliente.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa un producto de la tienda
 *
 * @author Libardo, Julio
 */
public class Product {

    //@JsonProperty("id")
    private Integer id;

    //@JsonProperty("name")
    private String name;

    //@JsonProperty("price")
    private Double price;

    public Product(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price
                + '}';
    }
}
