package shop;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    private String name;
    private double price;
    private double rating;
    private String nameOfCategory;
    private static int countProduct = 1;
    private int id;

    public Product(String name, double price, double rating, int id, String nameOfCategory) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.id = id;
        this.nameOfCategory = nameOfCategory;
        /*id = countProduct;
        countProduct++;*/
    }

    public Product() {
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

    public double getPrice() {
        return price;
    }

    public Double getRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getNameOfCategory() {
        return nameOfCategory;
    }

    public void setNameOfCategory(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", nameOfCategory='" + nameOfCategory + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                Double.compare(product.rating, rating) == 0 &&
                name.equals(product.name) &&
                nameOfCategory.equals(product.nameOfCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, rating);
    }
}
