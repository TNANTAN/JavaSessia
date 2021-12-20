package com.idk.fxs;

import com.idk.fxs.DBpakage.DataBaseHandler;
import com.idk.fxs.MiniScripts;
import com.idk.fxs.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Registration_Controller {

    @FXML
    private Button Login_Button;
    ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female");
    @FXML
    private ComboBox<String> gender;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Country_Text_Field;


    @FXML
    private TextField Login_Text_Field;

    @FXML
    private TextField Name_Text_Field;

    @FXML
    private PasswordField Passwd_PasswordField;

    @FXML
    private TextField Phone_Text_Field;

    @FXML
    private Button Regisrtr_Button;

    @FXML
    private TextField Surname_Text_Field;

    @FXML
    void initialize() {
         gender.setItems(genders);
        Regisrtr_Button.setOnAction(Event ->{
            RegistrationNewUser();
        } );
        Login_Button.setOnAction(Event->{
            MiniScripts.openNewSceneButt("Login_View.fxml", "Login",Login_Button);
        });
    }

    private void RegistrationNewUser() {
        DataBaseHandler dBHandler = new DataBaseHandler();
        String Name = Name_Text_Field.getText();
        String Surname = Surname_Text_Field.getText();
        String Login = Login_Text_Field.getText();
        String Password = Passwd_PasswordField.getText();
        String PhoneNumber = Phone_Text_Field.getText();
        String Country = Country_Text_Field.getText();
        String Gender = gender.getValue();
        User users = new User(Name,Surname,Login,Password,PhoneNumber,Country,Gender);
        boolean check = dBHandler.signUpUser(users);
        if (check){
           MiniScripts.openNewSceneButt("Login_View.fxml", "Login",Regisrtr_Button);
        }
    }

}
