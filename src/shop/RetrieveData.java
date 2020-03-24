package shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static shop.ConnectionData.*;

public class RetrieveData {
    private static final String SELECT_QUERY_USERS = "SELECT * FROM users;";
    private static final String SELECT_QUERY_CATEGORY = "SELECT * FROM catalog;";
    private static final String SELECT_QUERY_PRODUCT = "SELECT * FROM product;";

    public static User getUser() {
        User user = new User();
        try (Connection connection =
                     DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY_USERS);
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public static List<Category> getCategory() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection1 =
                     DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement1 = connection1.createStatement()) {
            ResultSet categorySet = statement1.executeQuery(SELECT_QUERY_CATEGORY);
            while (categorySet.next()) {
                categories.add(new Category(categorySet.getString("name"),
                        categorySet.getInt("id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try (Connection connection2 =
                     DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement2 = connection2.createStatement()) {
            ResultSet productSet = statement2.executeQuery(SELECT_QUERY_PRODUCT);
            List<Product> tempList = new ArrayList<>();
            while (productSet.next()) {
                for (Category category : categories) {
                    if (category.getId() == productSet.getInt("catalog_id")) {
                        tempList.add(new Product(productSet.getString("name"),
                                productSet.getDouble("price"),
                                productSet.getDouble("rate"),
                                productSet.getInt("id"),
                                category.getName()));
                    }
                }
            }
            for (Category category : categories) {
                for (Product product : tempList) {
                    if (category.getName() == product.getNameOfCategory()) {
                        category.add(new Product(product.getName(),
                                product.getPrice(),
                                product.getRating(),
                                product.getId(),
                                product.getNameOfCategory()));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }
}
