package shop.dao;

import java.sql.*;

import shop.model.User;


import static shop.dao.ConnectionData.*;

public class UserDAO {

    private static final String SELECT_QUERY_USERS = "SELECT * FROM users;";

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
}
