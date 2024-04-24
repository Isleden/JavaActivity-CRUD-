package com.example.login;

import java.sql.*;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloController {

    public Button btnLogout;
    public VBox pnLogin;
    private Label welcomeText;
    public Pane pnLogout;
    public ColorPicker cpPicker;
    public Pane pnRegisterForm;

    public Pane pnStartup;

    public PasswordField registerPass,logPass,updatePassword;
    public TextField registerName,logName,updateUsername;

    public static User user;
    @FXML
    protected void onHelloButtonClick() throws IOException {
//        AnchorPane p = (AnchorPane) pnLogin.getParent();
//        p.getScene().getStylesheets().clear();
//        Parent scene = FXMLLoader.load(getClass().getResource("homeView.fxml"));
//        p.getChildren().clear();
//        p.getChildren().add(scene);

        String username = logName.getText();
        String password = logPass.getText();
        boolean isValidCredentials = false;
        AnchorPane p = (AnchorPane) pnLogin.getParent();

        int retrievedUserID = 0;
        try (Connection c = MySQLConnection.getConnection();
             Statement statement = c.createStatement()) {
            String query = "SELECT * FROM users";
            ResultSet res = statement.executeQuery(query);
//
            while (res.next()) {
                String username2 = res.getString("name");
                String password2 = res.getString("password");

                if (username.equals(username2) && password.equals(password2)) {
                    isValidCredentials = true;
                    retrievedUserID = res.getInt("id");
                    user = new User(username2,password2,retrievedUserID);
                    break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (isValidCredentials) {
            p = (AnchorPane) pnLogin.getParent();
            p.getScene().getStylesheets().clear();
            Parent scene = FXMLLoader.load(getClass().getResource("homeView.fxml"));
            scene.prefHeight(p.getScene().getHeight());
            scene.prefWidth(p.getScene().getWidth());
            p.getChildren().clear();
            p.getChildren().add(scene);
        }
    }
    public void onRegisterViewButtonClick() throws IOException {
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
        AnchorPane p = (AnchorPane) pnLogin.getParent();
        p.getScene().getStylesheets().clear();

        Parent scene = FXMLLoader.load(getClass().getResource("register-view.fxml"));
        p.getChildren().clear();
        p.getChildren().add(scene);
    }

    public void onLogout(ActionEvent actionEvent) throws IOException{
        AnchorPane p = (AnchorPane) pnLogout.getParent();
        Parent scene = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        p.getChildren().clear();
        p.getChildren().add(scene);
    }

    public void registerAccount() throws IOException {
        String username = registerName.getText();
        String password = registerPass.getText();
        AnchorPane p;
        try(Connection c = MySQLConnection.getConnection();
            PreparedStatement statement = c.prepareStatement(
                    "INSERT into users (name, password) VALUES (?,?)")){

            statement.setString(1,username);
            statement.setString(2, password);
            int rows = statement.executeUpdate();

            if(rows > 0){
                System.out.println("Rows inserted: " + rows);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        p = (AnchorPane) pnRegisterForm.getParent();
        Parent scene = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        p.getChildren().clear();
        p.getChildren().add(scene);

    }

    public void updateDetails()
    {
        String newPassword = updatePassword.getText();
        int currentUserId = user.getId();
        String newUsername = updateUsername.getText();
        UpdateData updateData = new UpdateData();
        try {
            updateData.updatePassword(currentUserId, newPassword);
            updateData.updateUsername(currentUserId, newUsername);
            System.out.println("Details Updated!");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}