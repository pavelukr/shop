package shop.model;

import java.time.LocalDate;
import java.text.NumberFormat;
import java.util.*;

public class User {
    private String login;
    private String password;
    private int id;
    private Basket basket;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.basket = new Basket();
    }

    public User() {
        basket = new Basket();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public void boughtProducts() {
        Locale locale = Locale.getDefault();
        LocalDate today = LocalDate.now();
        System.out.printf("%-39s %-20s", "Дата", today);
        System.out.println();
        System.out.println();
        ResourceBundle bundle = ResourceBundle.getBundle("topic", locale);

        for (String key : bundle.keySet()) {
            System.out.printf("%-20s", bundle.getString(key));
        }

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        System.out.println();
        System.out.println("-----------------------------------------------");
        double finalPrice = 0;
        for (Product v : basket.getProducts().values()) {
            System.out.printf("%-19s %-19s %-20s",
                    v.getName(), v.getNameOfCategory(),
                    numberFormat.format(v.getPrice()));
            System.out.println();
            finalPrice += v.getPrice();
        }

        System.out.println("-----------------------------------------------");
        System.out.printf("%-39s %-20s", "Итого:",
                numberFormat.format(finalPrice));
        System.out.println();
    }

    @Override
    public String toString() {
        return "User{"
                + "login='" + login + '\''
                + ", password='" + password + '\''
                + ", basket=" + basket
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(basket, user.basket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, basket);
    }
}
