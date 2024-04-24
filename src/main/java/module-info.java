module com.example.login {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.login to javafx.fxml;
    exports com.example.login;
}