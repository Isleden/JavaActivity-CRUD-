package com.example.login;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class RegisterView {
    public Label registerName;

    public PasswordField registerPass;

    public static void main(String[] args)
    {
        try(Connection c = MySQLConnection.getConnection();
            PreparedStatement statement = c.prepareStatement(
                    "INSERT into users (name, email) VALUES (?,?)")){
            String name = "Alden Niño N. Cabrera";
            String email = "alden.cabrera@cit.edu";
            statement.setString(1,name);
            statement.setString(2,email);
            int rows = statement.executeUpdate();
            System.out.println("Rows inserted: " + rows);
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
