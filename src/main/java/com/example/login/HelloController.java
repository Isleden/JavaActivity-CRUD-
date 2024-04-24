package com.example.login;




import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HelloController {

    public Button btnLogout;
    public VBox pnLogin;
    private Label welcomeText;
    public Pane pnLogout;
    public ColorPicker cpPicker;
    public Pane pnRegisterForm;

    public Pane pnStartup;
    @FXML
    protected void onHelloButtonClick() throws IOException {
        AnchorPane p = (AnchorPane) pnLogin.getParent();
        p.getScene().getStylesheets().clear();
//        p.getScene().getStylesheets().add(getClass().getResource("userStyle.css").toExternalForm());
        Parent scene = FXMLLoader.load(getClass().getResource("homeView.fxml"));
        p.getChildren().clear();
        p.getChildren().add(scene);
    }
    public void onRegisterViewButtonClick() throws IOException {
        AnchorPane p = (AnchorPane) pnLogin.getParent();
        p.getScene().getStylesheets().clear();
//        Parent scene = FXMLLoader.load(getClass().getResource("register-view.fxml"));
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


}