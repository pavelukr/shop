package shop;

import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.io.Serializable;

public class Basket implements Serializable{
    private SortedMap<Integer, Product> products;

    public Basket(SortedMap<Integer, Product> products) {
        this.products = products;
    }

    public Basket() {
        products = new TreeMap<>();
    }

    public SortedMap<Integer, Product> getProducts() {
        return products;
    }

    public void setProducts(SortedMap<Integer, Product> products) {
        this.products = products;
    }

    public void add(Product product) {
        products.put(product.getId(), product);
    }

    public Product getProduct(int key) {
        return products.get(key);
    }

    @Override
    public String toString() {
        return "Basket{" +
                "category=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return Objects.equals(this.products, basket.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }
}
