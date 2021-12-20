package com.idk.fxs.ServerClients;

import com.idk.fxs.User;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Clients {
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private String username;

    public Clients(Socket socket, String username) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
        }catch (IOException e ){
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    public void sendMsg(User user){
       // new Thread(()->{
            try {
                if (username != null) {
                    bufferedWriter.write(user.getName());
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                Scanner sc = new Scanner(System.in);
                while (socket.isConnected()){
                    if (user.getRole().equals("1")){
                        break;
                    }
                    String msgToSend = sc.nextLine();
                    if (!msgToSend.isEmpty()){
                        bufferedWriter.write(user.getName()+ ": " + msgToSend);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                }
            }catch (IOException e ){
                closeEverything(socket,bufferedReader,bufferedWriter);
            }
       //).start();
    }
    public void lstForMsg(){
        new Thread(() -> {
            String msgFGroup;
            while (socket.isConnected()){
                try {
                    msgFGroup = bufferedReader.readLine();
                    System.out.println(msgFGroup);
                }catch (IOException e){
                    closeEverything(socket,bufferedReader,bufferedWriter);
                }
            }
        }).start();
    }
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        try {
            if (bufferedReader!= null){
                bufferedReader.close();
            }
            if (bufferedWriter != null){
                bufferedWriter.close();
            }
            if(socket!= null){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
   /* public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Username: ");
        String username = sc.nextLine();
        Socket socket = new Socket("127.0.0.1", 62001);
        Clients clients = new Clients(socket, username);
        clients.lstForMsg();
        clients.sendMsg();
    }*/
}