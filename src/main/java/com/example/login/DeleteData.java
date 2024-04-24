package com.example.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteData {
    public static void main(String[] args) {
        try(Connection c = MySQLConnection.getConnection();
            PreparedStatement statement = c.prepareStatement(
                    "DELETE FROM users WHERE id=? RETURNING *"
            )){
            int id = 2;
            statement.setInt(1,id);
            int rows = statement.executeUpdate();
            ResultSet res = statement.getResultSet();
            if(res.next()){
                System.out.println("Name: " + res.getString("name"));
                System.out.println("Password: "+ res.getString("password"));
            }
            System.out.println("Rows Deleted: " + rows);


        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
