package shop.dao;

import shop.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static shop.dao.ConnectionData.*;
import static shop.dao.HistoryDAO.deleteHistory;
import static shop.dao.ConnectionData.PASSWORD;

public class CategoryDAO {

    private static final String SELECT_QUERY_CATEGORY = "SELECT * FROM catalog;";


    public static List<Category> getCategory() {
        deleteHistory();
        List<Category> categories = new ArrayList<>();
        try (Connection connection =
                     DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet categorySet =
                    statement.executeQuery(SELECT_QUERY_CATEGORY);
            while (categorySet.next()) {
                categories.add(new Category(categorySet.getString("name"),
                        categorySet.getInt("id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }

}
