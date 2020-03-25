package shop;

import java.sql.*;
import java.time.LocalDate;
import static shop.ConnectionData.*;

public class InsertData {
    public static void insertData(User user) {
        try (Connection connection =
                     DriverManager.getConnection(URL, USER, PASSWORD)){
            Date date = Date.valueOf(LocalDate.now());
            int i = 1;
            String insertHistorySQL = "INSERT INTO history(id, product_id, amount, date, user_id) "
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement productStmt = connection.prepareStatement(insertHistorySQL);
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