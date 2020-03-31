package shop.dao;

import shop.model.Category;
import shop.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static shop.dao.CategoryDAO.getCategory;
import static shop.dao.ConnectionData.*;

public class ProductDAO {

    private static final String SELECT_QUERY_PRODUCT = "SELECT * FROM product;";


    public static List<Category> productToCategory() {
        List<Category> categories = getCategory();
        try (Connection connection =
                     DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet productSet =
                    statement.executeQuery(SELECT_QUERY_PRODUCT);
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
            puttingProducts(categories, tempList);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }

    public static void puttingProducts(List<Category> categories, List<Product> tempList) {
        for (Category category : categories) {
            for (Product product : tempList) {
                if (category.getName().equals(product.getNameOfCategory())) {
                    category.add(new Product(product.getName(),
                            product.getPrice(),
                            product.getRating(),
                            product.getId(),
                            product.getNameOfCategory()));
                }
            }
        }
    }
}
