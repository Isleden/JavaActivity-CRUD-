package com.example.login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class InsertData {
    public static void main(String[] args) {
        try(Connection c = MySQLConnection.getConnection();
            PreparedStatement statement = c.prepareStatement(
                    "INSERT into users (name, email) VALUES (?,?)")){
            String name = "Alden Cabrera";
            String password = "qweqwe";
            statement.setString(1,name);
            statement.setString(2, password);
            int rows = statement.executeUpdate();

            if(rows > 0){
                System.out.println("Rows inserted: " + rows);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
