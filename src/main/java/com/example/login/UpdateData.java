package com.example.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateData
{
    public void updateUsername(int userIdToUpdate, String newUsername) throws SQLException {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE users SET name = ? WHERE id = ?")) {

            preparedStatement.setString(1, newUsername);
            preparedStatement.setInt(2, userIdToUpdate);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Data updated successfully!");
            }
        }
    }

    public void updatePassword(int userIdToUpdate, String newPassword) throws SQLException {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE users SET password = ? WHERE id = ?")) {

            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, userIdToUpdate);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Password updated successfully!");
            }
        }
    }

    public void updateShowDetails(int showIDToUpdate, String newShowName,String newShowStatus, int newShowNumOfSeasons) throws SQLException {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE tblfavoriteshow SET showname = ?,status = ?,numOfSeasons = ? WHERE showid = ?")) {

            preparedStatement.setString(1, newShowName);
            preparedStatement.setString(2, newShowStatus);
            preparedStatement.setInt(3, newShowNumOfSeasons);
            preparedStatement.setInt(4, showIDToUpdate);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Show details updated successfully!");
            }
        }
    }


}
