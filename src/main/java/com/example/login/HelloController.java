package com.example.login;

import java.sql.*;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    public Button btnLogout;
    public VBox pnLogin;
    private Label welcomeText;
    @FXML
    public Pane pnLogout,pnMainMenu,pnRegisterForm,pnDeletePage,pnStartUp,pnAccView,pnRegisterShowForm,pnShowMainMenu,pnShowUpdate,pnShowDeletePage;
    public ColorPicker cpPicker;
//    public Pane pnRegisterForm;
//    public Pane pnDeletePage;
//    public Pane pnStartup;

    public PasswordField registerPass,logPass,updatePassword;
    public TextField registerName,logName,updateUsername,showName,showStatus,showSeasons,txtShowID,updateShowName,updateShowStatus,updateShowNumOfSeasons,showNameToDelete;

    public static User user;
    public static Show show;
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
            Parent scene = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
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
        if(pnLogout != null) {
            AnchorPane p = (AnchorPane) pnLogout.getParent();
            Parent scene = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            p.getChildren().clear();
            p.getChildren().add(scene);
        }
        else if(pnMainMenu != null)
        {
            AnchorPane p = (AnchorPane) pnMainMenu.getParent();
            Parent scene = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            p.getChildren().clear();
            p.getChildren().add(scene);
        }
        else if(pnShowMainMenu != null)
        {
            AnchorPane p = (AnchorPane) pnShowMainMenu.getParent();
            Parent scene = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            p.getChildren().clear();
            p.getChildren().add(scene);
        }
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
    public void returnToMainMenu(ActionEvent actionEvent) throws IOException{
        AnchorPane p = (AnchorPane) pnLogout.getParent();
        Parent scene = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        p.getChildren().clear();
        p.getChildren().add(scene);
    }
    public void redirectToUpdate(ActionEvent actionEvent) throws IOException
    {

        if (pnMainMenu != null) {
            AnchorPane p = (AnchorPane) pnMainMenu.getParent();
            Parent scene = FXMLLoader.load(getClass().getResource("homeView.fxml"));
            p.getChildren().clear();
            p.getChildren().add(scene);
        }
    }

    public void redirectToDelete(ActionEvent actionEvent) throws IOException
    {
//        AnchorPane p = (AnchorPane) pnMainMenu.getParent();
//        Parent scene = FXMLLoader.load(getClass().getResource("homeView.fxml"));
//        p.getChildren().clear();
//        p.getChildren().add(scene);

        if (pnMainMenu != null) {
            AnchorPane p = (AnchorPane) pnMainMenu.getParent();
            Parent scene = FXMLLoader.load(getClass().getResource("deleteconfirmation.fxml"));
            p.getChildren().clear();
            p.getChildren().add(scene);
        }
    }

    public void deleteAccAffirmative() throws IOException {
        AnchorPane p = (AnchorPane) pnDeletePage.getParent();
        try(Connection c = MySQLConnection.getConnection();
            PreparedStatement statement = c.prepareStatement(
                    "DELETE FROM users WHERE id=? RETURNING *"
            )){
            int id = user.getId();
            statement.setInt(1,id);
            int rows = statement.executeUpdate();
            ResultSet res = statement.getResultSet();
            if(res.next()){
                System.out.println("User successfully deleted!");
                System.out.println("Name: " + res.getString("name"));
                System.out.println("Password: "+ res.getString("password"));
            }
            System.out.println("Rows Deleted: " + rows);


        }catch (SQLException e){
            e.printStackTrace();
        }
        Parent scene = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        p.getChildren().clear();
        p.getChildren().add(scene);
    }

    public void deleteAccNegative(ActionEvent actionEvent) throws IOException
    {
            AnchorPane p = (AnchorPane) pnDeletePage.getParent();
            Parent scene = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            p.getChildren().clear();
            p.getChildren().add(scene);
    }
    @FXML
    public void viewAccounts() throws IOException {
        AnchorPane p = (AnchorPane) pnMainMenu.getParent();
        Parent scene = FXMLLoader.load(getClass().getResource("viewAcc.fxml"));
        p.getChildren().clear();
        p.getChildren().add(scene);

        try (Connection c = MySQLConnection.getConnection();
             Statement statement = c.createStatement()) {
            String query = "SELECT * FROM users";
            ResultSet res = statement.executeQuery(query);


            List<User> userList = new ArrayList<>();

            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                String password = res.getString("password");


                userList.add(new User(name, password,id));
            }

            TableView<User> userTableView = new TableView<>();

            TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            idColumn.setPrefWidth(100);

            TableColumn<User, String> nameColumn = new TableColumn<>("Username");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            nameColumn.setPrefWidth(100);

            TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
            passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
            passwordColumn.setPrefWidth(100);


            userTableView.getColumns().addAll(idColumn, nameColumn, passwordColumn);
            userTableView.setItems(FXCollections.observableArrayList(userList));


            userTableView.setPrefWidth(300);

            p.getChildren().add(userTableView);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewToMain(ActionEvent actionEvent) throws IOException
    {
        AnchorPane p = (AnchorPane) pnAccView.getParent();
        Parent scene = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        p.getChildren().clear();
        p.getChildren().add(scene);
    }

    public void toAddFavShow() throws IOException
    {

        AnchorPane p = (AnchorPane) pnMainMenu.getParent();
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
        Parent scene = FXMLLoader.load(getClass().getResource("registerShow.fxml"));
        p.getChildren().clear();
        p.getChildren().add(scene);
    }

    public void registerShow() throws IOException
    {
        String rShowName = showName.getText();
        String rShowStatus = showStatus.getText();
        int rShowSeasons = Integer.parseInt(showSeasons.getText());

        int userId = user.getId();

        try (Connection connection = MySQLConnection.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO tblfavoriteshow (id, showname, status, numOfSeasons) VALUES (?, ?, ?, ?)")) {

                statement.setInt(1, userId);
                statement.setString(2, rShowName);
                statement.setString(3, rShowStatus);
                statement.setInt(4, rShowSeasons);
                int rows = statement.executeUpdate();

                if (rows > 0) {
                    System.out.println("Rows inserted: " + rows);
                    System.out.println("User's favorite show added!");
                }

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException("Transaction failed.", e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error.", e);
        }
        AnchorPane p = (AnchorPane) pnRegisterShowForm.getParent();
        Parent scene = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        p.getChildren().clear();
        p.getChildren().add(scene);
    }
    public void redirectToShowUpdate(ActionEvent actionEvent) throws IOException
    {

        if (pnShowMainMenu != null) {
            AnchorPane p = (AnchorPane) pnShowMainMenu.getParent();
            Parent scene = FXMLLoader.load(getClass().getResource("showUpdate.fxml"));
            p.getChildren().clear();
            p.getChildren().add(scene);
        }
    }

    public void switchMainMenu(ActionEvent actionEvent) throws IOException{
        AnchorPane p = (AnchorPane) pnMainMenu.getParent();
        Parent scene = FXMLLoader.load(getClass().getResource("showMainMenu.fxml"));
        p.getChildren().clear();
        p.getChildren().add(scene);
    }

    public void redirectShowUpdate(ActionEvent actionEvent) throws IOException{
        AnchorPane p = (AnchorPane) pnShowMainMenu.getParent();
        Parent scene = FXMLLoader.load(getClass().getResource("showUpdate.fxml"));
        p.getChildren().clear();
        p.getChildren().add(scene);
    }

    public void onUpdateShow() {
        int showID = Integer.parseInt(txtShowID.getText());
        String newShowName = updateShowName.getText();
        String newShowStatus = updateShowStatus.getText();
        int newNumOfSeasons = Integer.parseInt(updateShowNumOfSeasons.getText());
        UpdateData updateData = new UpdateData();
        try {
            updateData.updateShowDetails(showID, newShowName,newShowStatus,newNumOfSeasons);
            System.out.println("Show details updated!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnToShowMainMenu(ActionEvent actionEvent) throws IOException{
        AnchorPane p = (AnchorPane) pnShowUpdate.getParent();
        Parent scene = FXMLLoader.load(getClass().getResource("showMainMenu.fxml"));
        p.getChildren().clear();
        p.getChildren().add(scene);
    }

    public void switchBackMainMenu(ActionEvent actionEvent) throws IOException{
        AnchorPane p = (AnchorPane) pnShowMainMenu.getParent();
        Parent scene = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        p.getChildren().clear();
        p.getChildren().add(scene);
    }

    public void redirectToShowDelete(ActionEvent actionEvent) throws IOException
    {
//        AnchorPane p = (AnchorPane) pnMainMenu.getParent();
//        Parent scene = FXMLLoader.load(getClass().getResource("homeView.fxml"));
//        p.getChildren().clear();
//        p.getChildren().add(scene);

        if (pnShowMainMenu != null) {
            AnchorPane p = (AnchorPane) pnShowMainMenu.getParent();
            Parent scene = FXMLLoader.load(getClass().getResource("deleteShowConfirmation.fxml"));
            p.getChildren().clear();
            p.getChildren().add(scene);
        }
    }

    public void deleteShowAffirmative() throws IOException {
        AnchorPane p = (AnchorPane) pnShowDeletePage.getParent();
        Parent scene = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        p.getChildren().clear();
        p.getChildren().add(scene);
//        try(Connection c = MySQLConnection.getConnection();
//            PreparedStatement statement = c.prepareStatement(
//                    "DELETE FROM tblfavoriteshow WHERE id=? RETURNING *"
//            )){
//            int id = user.getId();
//            statement.setInt(1,id);
//            int rows = statement.executeUpdate();
//            ResultSet res = statement.getResultSet();
//            if(res.next()){
//                System.out.println("User successfully deleted!");
//                System.out.println("Name: " + res.getString("name"));
//                System.out.println("Password: "+ res.getString("password"));
//            }
//            System.out.println("Rows Deleted: " + rows);
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
        String showRemove = showNameToDelete.getText();

        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "DELETE FROM tblfavoriteshow WHERE showname = ? AND id = ? RETURNING *")) {
            statement.setString(1, showRemove);
            statement.setInt(2, user.getId());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Show deleted!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void deleteShowNegative(ActionEvent actionEvent) throws IOException
    {
        AnchorPane p = (AnchorPane) pnShowDeletePage.getParent();
        Parent scene = FXMLLoader.load(getClass().getResource("showMainMenu.fxml"));
        p.getChildren().clear();
        p.getChildren().add(scene);
    }


}