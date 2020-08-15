package shop.dao;

import shop.model.User;
import shop.model.Product;

import java.sql.*;
import java.time.LocalDate;

import static shop.dao.ConnectionData.*;

public class HistoryDAO {

    private static final String DELETE_QUERY_HISTORY = "SELECT * FROM history;";

    public static void deleteHistory() {
        try (Connection connection =
                     DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement stmt =
                    connection.prepareStatement(DELETE_QUERY_HISTORY,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                rs.deleteRow();
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertData(User user) {
        try (Connection connection =
                     DriverManager.getConnection(URL, USER, PASSWORD)) {
            Date date = Date.valueOf(LocalDate.now());
            int i = 1;
            String insertHistorySQL =
                    "INSERT INTO history(id, product_id, amount, date, user_id) VALUES (?,?,?,?,?)";
            PreparedStatement productStmt =
                    connection.prepareStatement(insertHistorySQL);
            for (Product product : user.getBasket().getProducts().values()) {
                productStmt.setInt(1, i);
                productStmt.setInt(2, product.getId());
                productStmt.setInt(3, product.getAmount());
                productStmt.setDate(4, date);
                productStmt.setInt(5, user.getId());
                productStmt.addBatch();
                i++;
            }
            productStmt.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
