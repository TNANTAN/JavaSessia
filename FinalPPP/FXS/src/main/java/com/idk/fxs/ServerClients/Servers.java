package com.idk.fxs.ServerClients;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servers {
    private  ServerSocket serverSocket;
    public Servers(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }
    public void startServer(){
        try {
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("Client Connected");
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }catch (IOException e){
           // closeEverything();
        }

    }
    public void closeServerSocket(){
        try {
            if (serverSocket!= null){
                serverSocket.close();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(62001);
        Servers servers = new Servers(serverSocket);
        servers.startServer();
    }
}