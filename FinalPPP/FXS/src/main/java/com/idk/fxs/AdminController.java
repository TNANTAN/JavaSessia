package com.idk.fxs;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController {
    @FXML
    private Button Client_Butt;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label AuthLabel;

    @FXML
    private Button Login_Button;

    @FXML
    private TextField Login_Text_Field;

    @FXML
    private PasswordField Passwd_PasswordField;

    @FXML
    void initialize() {
        Client_Butt.setOnAction(Event->{
            MiniScripts.openNewSceneButt("Login_View.fxml", "Login", Client_Butt);
        });
        Login_Button.setOnAction(actionEvent -> {
             MiniScripts.LoginUserAdmin(Login_Text_Field.getText(),Passwd_PasswordField.getText(), Login_Button, AuthLabel);
        });
    }
}
