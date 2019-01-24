package com.example.alireza.taskmanager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class TmServer extends Thread {
    public static Vector<TmClientHandler> clientHandlers = new Vector<>();
    public static ArrayList<ServerUser> user = new ArrayList<ServerUser>();
    public static int numberOfActiveClients = 0;

    public static void main(String[] args) throws IOException {
        //Use next line to read from Server:
        //ObjectInputStream a = new ObjectInputStream(new FileInputStream("D:\\New.ser"));

        ServerSocket serverSocket = new ServerSocket(4343);
        Socket clientSocket = null;

        while (true) {
            clientSocket = serverSocket.accept();
            System.out.println("Connection Established!");
            DataInputStream InputServer = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream OutputServer = new DataOutputStream(clientSocket.getOutputStream());
            TmClientHandler clientHandler = new TmClientHandler (clientSocket, InputServer, OutputServer);
            Thread thread = new Thread(clientHandler);
            thread.start();
            clientHandlers.add(clientHandler);
            numberOfActiveClients++;
        }
    }
}
