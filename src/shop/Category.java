package shop;

import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

public class Category {
    private SortedMap<Integer, Product> products;
    private static int count = 1;
    private int id;
    private String name;

    public Category(String name, int id) {
        //this();
        this.id = id;
        this.name = name;
        products = new TreeMap<>();
    }

    public Category() {
        /*id = count;
        count++;*/
    }

    public SortedMap<Integer, Product> getProducts() {
        return products;
    }

    public void setProducts(SortedMap<Integer, Product> products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(Product product) {
        //product.setNameOfCategory(name);
        products.put(product.getId(), product);
    }

    public Product getProduct(int key) {
        return products.get(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category1 = (Category) o;
        return id == category1.id &&
                Objects.equals(products, category1.products) &&
                Objects.equals(name, category1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products, id, name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "category=" + products +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}