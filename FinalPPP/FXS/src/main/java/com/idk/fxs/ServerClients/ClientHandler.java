package com.idk.fxs.ServerClients;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String Username;
    private String Status;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.Username = bufferedReader.readLine();
            clientHandlers.add(this);
            broadcastMessage("Server: " + Username + " Has added");
        }catch (IOException e){
            closeEverything(socket, bufferedReader,bufferedWriter);
        }
    }

    @Override
    public void run() {
        String msgFClient;
        while ((socket.isConnected())){
            try {
                msgFClient = bufferedReader.readLine();
                broadcastMessage(msgFClient);
            }catch (IOException e){
                closeEverything(socket, bufferedReader,bufferedWriter);
                break;
            }
        }
    }

    public void broadcastMessage(String msgToSend){
        for(ClientHandler clientHandler: clientHandlers){
            try {
                if(!clientHandler.Username.equals(Username)){
                    String date = new Date().toString().substring(4,16);
                    msgToSend += "  | " + date;
                    clientHandler.bufferedWriter.write(msgToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            }catch (IOException e){
                closeEverything(socket,bufferedReader,bufferedWriter);
            }
        }
    }
    public void removeClientHands(){
        clientHandlers.remove(this);
        broadcastMessage("Server: " + Username + " Left");
    }
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        removeClientHands();
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
}