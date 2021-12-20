package com.idk.fxs;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class NewsController {

    @FXML
    private Button FriendsButton;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AccountButton;

    @FXML
    private AnchorPane AnchPaneVboxPane;

    @FXML
    private Button MessengerButton;

    @FXML
    private Button NewButton;

    @FXML
    private Button ServiceButton;

    @FXML
    private VBox VBoxBorderDown;

    @FXML
    private BorderPane firstBorderPane;

    @FXML
    void initialize() {
     MessengerButton.setOnAction(Event->{
         MiniScripts.openNewSceneButtNotStream("Messenger.fxml","Messenger",NewButton);
     });
    }
}
