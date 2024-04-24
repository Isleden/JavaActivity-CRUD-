package com.example.login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
public class CreateTable
{
    public static void main(String[] args) {
        try(Connection c = MySQLConnection.getConnection();
            Statement statement = c.createStatement()){

            String query = "CREATE TABLE users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL," +
                    "password VARCHAR(100) NOT NULL)";
            statement.execute(query);
            System.out.println("Table created successfully!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void createShowTable()
    {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement("CREATE TABLE IF NOT EXISTS tblFavoriteShow (" +
                     "showid INT AUTO_INCREMENT PRIMARY KEY," +
                     "id INT NOT NULL," +
                     "FOREIGN KEY (id) REFERENCES users(id)," +
                     "showname VARCHAR(100) NOT NULL," +
                     "status VARCHAR(100) NOT NULL," +
                     "numOfSeasons INT NOT NULL" +
                     ")")) {
            statement.execute();
            System.out.println("2nd Table created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
