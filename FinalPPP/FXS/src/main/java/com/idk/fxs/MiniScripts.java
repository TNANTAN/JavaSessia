package com.idk.fxs;


import com.idk.fxs.DBpakage.Const;
import com.idk.fxs.DBpakage.DataBaseHandler;
import com.idk.fxs.ServerClients.Clients;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public  class MiniScripts  {
    public static void openNewSceneButt(String window, String title, Button button){
        button.getScene().getWindow().hide();
        button.setVisible(false);
        InputStream stream = MiniScripts.class.getResourceAsStream(window);
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void PLayMedia(String path, MediaView mediaView, Button startButt, Button resetButt){
        File file = new File(path);
        Media media = new Media(file.toURI().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        playVideoActions(startButt, resetButt,mediaPlayer);
        //resetMedia(resetButt, mediaPlayer);
    }


    public static void playVideoActions(Button startButt, Button resetButt , MediaPlayer mediaPlayer){
        startButt.setOnAction(Event->{
            if (mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING){
                startButt.setText("Pause");
                mediaPlayer.play();
            }
            else {
                startButt.setText("Play");
                mediaPlayer.pause();
            }
        });
            resetButt.setOnAction(Event->{
                if (mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
                    mediaPlayer.seek(Duration.seconds(0.0));
                }
            });
    }

    public static void openNewSceneButtNotStream(String window, String title, Button button){
        button.getScene().getWindow().hide();
        //button.setVisible(false);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Controller_Login.class.getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static Object LoginUser(String loginText, String PasswordText,Button button, Label label) {
        Clients clients;
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
                user.setStatus(rs.getString(Const.USER_STATUS));
                user.setRole(rs.getString(Const.USER_ROLE));
                user.setId(Integer.parseInt(rs.getString(Const.USER_ID)));
                user.setSurName(rs.getString(Const.USER_SURNAME));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user.getStatus() != null && user.getStatus().equals("L")) {
            label.setText("Вас заблокировали в этой группе из за нарушение правил!!!");
            label.setFont(new Font("Lucida Calligraphy",16));
            System.out.println("Вас заблокировали в этой группе из за нарушение правил!!!");
            return null;
        }
        if (user.getRole()!=null && user.getRole().equals("1")){
            label.setText("Зайдите как Админ!!!");
            System.out.println("Зайдите как Админ!");
            return null;
        }
        if (counter > 0) {
            Socket socket = null;
            try {
                socket = new Socket("127.0.0.1", 62001);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert socket != null;
            button.getScene().getWindow().hide();
            System.out.println(user.getName());
            clients = new Clients(socket, user.getName());
            clients.lstForMsg();
            clients.sendMsg(user);
            //openNewSceneButt("Messenger.fxml","mess",button);
        } else {
            System.out.println("NotFound");
        }
        return null;
    }
    public static void DeleteUser(String loginText, String PasswordText) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(PasswordText);
        dbHandler.deleteUser(user);
    }
    public static void DeleteUserID(String Id) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setId(Integer.parseInt(Id));
        dbHandler.deleteUserId(user);
    }

    public static Object LoginUserAdmin(String loginText, String PasswordText,Button button, Label label) {
        Clients clients;
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
                user.setSurName(rs.getString(Const.USER_SURNAME));
                user.setStatus(rs.getString(Const.USER_STATUS));
                user.setRole(rs.getString(Const.USER_ROLE));
                user.setId(Integer.parseInt(rs.getString(Const.USER_ID)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user.getRole() != null && user.getRole().equals("0")) {
            label.setLayoutX(200.9);
            label.setText("Вы не Админ");
            System.out.println("Вы не Админ");
            return null;
        }
        if (user.getStatus() != null && user.getStatus().equals("L")) {
            label.setFont(new Font("",16));
            label.setLayoutX(140.9);
            label.setText("Вас заблокировали в этой группе из за нарушение правил!!!");
            System.out.println("Вас заблокировали в этой группе из за нарушение правил!!!");
            return null;
        }

        if (counter > 0) {
            Socket socket = null;
            try {
                socket = new Socket("127.0.0.1", 62001);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert socket != null;
            button.getScene().getWindow().hide();
            System.out.println(user.getName());
            clients = new Clients(socket, user.getName());
            clients.sendMsg(user);
            //openNewSceneButt("Messenger.fxml","mess",button);
        } else {
            System.out.println("NotFound");
        }
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("""
                            [0]EXIT
                            [1]Get All Users
                            [2]Delete User
                            [3]Block User
                            [4]UnBlock User""");
            int answ = sc.nextInt();
            if (answ == 0){
                return null;
            }
            adminAction(answ, dbHandler);
        }
    }
    private static void adminAction(int answer, DataBaseHandler dataBaseHandler){
        Scanner sc = new Scanner(System.in);
         if(answer == 1){
             ArrayList<User> users = dataBaseHandler.GetAllUsers();
            for(User u: users){
                System.out.println(u.toString());
            }
        }
        else if(answer == 2){
            System.out.println("User Id you want delete");
            DeleteUserID(sc.next());
        }
        else if(answer == 3){
            System.out.println("User ID you want to Block");
            BlockUserID(sc.next());
        }
        else if (answer == 4){
            System.out.println("User ID you want to Unblock");
            UnBlockUserID(sc.next());
        }
    }
    public static void BlockUserID(String Id) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setId(Integer.parseInt(Id));
        dbHandler.blockUserId(user);
    }
    public static void UnBlockUserID(String Id) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setId(Integer.parseInt(Id));
        dbHandler.unblockUserId(user);
    }
}
