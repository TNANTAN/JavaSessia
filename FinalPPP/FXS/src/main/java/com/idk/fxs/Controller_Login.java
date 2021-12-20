package com.idk.fxs;
import com.idk.fxs.ServerClients.Clients;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_Login {
    @FXML
    private Button AdminButt;
    @FXML
    private Label AuthLabel;
    @FXML
        private Button deleteButt;
        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button Login_Button;

        @FXML
        private TextField Login_Text_Field;

        @FXML
        private PasswordField Passwd_PasswordField;

        @FXML
        private Button Regisrtr_Button;

        public Clients clients;

    @FXML
    void initialize() {
        deleteButt.setOnAction(Even->{
        MiniScripts.DeleteUser(Login_Text_Field.getText(), Passwd_PasswordField.getText());
        });

        Regisrtr_Button.setOnAction(even -> {
            MiniScripts.openNewSceneButt("Registration.fxml", "Registration", Regisrtr_Button);
        });
         Login_Button.setOnAction(Event -> {
         MiniScripts.LoginUser(Login_Text_Field.getText(), Passwd_PasswordField.getText(), Login_Button,AuthLabel );
        });
         AdminButt.setOnAction(actionEvent -> {
             MiniScripts.openNewSceneButt("AdminView.fxml", "Administration", AdminButt);
         });
    }
    /*private void LoginUser(String loginText, String PasswordText) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(PasswordText);
        ResultSet rs = dbHandler.getUser(user);
        int counter = 0;
        try {
            while (rs.next()) {
                counter++;
                user.setName(rs.getString(Const.USER_NAME));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (counter > 0) {
            Socket socket = null;
            try {
                socket = new Socket("127.0.0.1", 62001);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert socket != null;
            Login_Button.getScene().getWindow().hide();
            System.out.println(user.getName());
            clients = new Clients(socket, user.getName());
            clients.lstForMsg();
            clients.sendMsg();
        } else {
            System.out.println("NotFound");
        }
        //return clients;
    }*/
    /*private void DeleteUser(String loginText, String PasswordText) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(PasswordText);
        dbHandler.deleteUser(user);
    }*/
}

