package com.idk.fxs;


import com.idk.fxs.Controller_Login;
import com.idk.fxs.MiniScripts;
import com.idk.fxs.ServerClients.Clients;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class MessengerController extends Controller_Login implements Initializable {

    @FXML
    private Button sendButton;
    @FXML
    private TextField MessageTextField;
    @FXML
    private VBox vbox_message;
    @FXML
    private ScrollPane ScrPane_main;
    @FXML
    private AnchorPane anchpane2;

    @FXML
    private AnchorPane anchPaneCenter;
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
    private Button FriendsButton;

    @FXML
    private VBox VBoxBorderDown;

    @FXML
    private BorderPane firstBorderPane;
    //private static Clients clients;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NewButton.setOnAction(Event->{
            MiniScripts.openNewSceneButt("News.fxml","News", NewButton);
        });
       /* Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",62001);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert socket != null;*/
        vbox_message.heightProperty().addListener((observableValue, oldValue, newValue) -> ScrPane_main.setVvalue((Double)(newValue)));
        //clients.lstForMsg(vbox_message);
        sendButton.setOnAction(actionEvent -> {
            String messageToSend = MessageTextField.getText();
            if (!messageToSend.isEmpty()){
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_RIGHT);
                hBox.setPadding(new Insets(5,5,5,10));
                Text text = new Text(messageToSend);
                TextFlow textFlow = new TextFlow(text);
                textFlow.setStyle("-fx-color: rgb(239,242,255);" +
                        "-fx-background-color: rgb(15,125,242);"+
                        " -fx-background-radius: 20px;");
                textFlow.setPadding(new Insets(5,10,5,10));
                text.setFill(Color.color(0.934,0.945, 0.966));
                hBox.getChildren().add(textFlow);
                vbox_message.getChildren().add(hBox);
                //clients.sendMsg(messageToSend);
                MessageTextField.clear();
            }
        });
    }
    public static void addLabel(String MessageToClient, VBox vBox){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BASELINE_LEFT);
        hBox.setPadding(new Insets(5,5,5,10));
        Text text = new Text(MessageToClient);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color: rgb(233,233,235);"+
                " -fx-background-radius: 20px;");
        textFlow.setPadding(new Insets(5,5,5,10));
        hBox.getChildren().add(textFlow);
        Platform.runLater(() -> {
            vBox.getChildren().add(hBox);
        });
    }
}








/*String Path = "C:\\Users\\ntana\\IdeaProjects\\experiment\\exp12\\src\\main\\resources\\com\\exp\\exp12\\Medias\\videoplayback.mp4";
        PLayMedia(Path, mdeiaView, startButt,resetButt);*/