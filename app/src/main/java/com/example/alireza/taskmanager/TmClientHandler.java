package com.example.alireza.taskmanager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.net.Socket;

public class TmClientHandler implements Runnable {
    final DataInputStream InputServer;
    final DataOutputStream OutputServer;
    Socket clientSocket;
    boolean isLoggedIn = false;
    String Username, Password, Message, Session = null;
    static BigInteger x = new BigInteger("0");

    public TmClientHandler(Socket clientSocket, DataInputStream InputServer, DataOutputStream OutputServer) {
        this.InputServer = InputServer;
        this.OutputServer = OutputServer;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message = InputServer.readUTF();
                if (Message.equals("register")) {
                    isLoggedIn = false;
                    Username = InputServer.readUTF();
                    Password = InputServer.readUTF();
                    for (ServerUser user : TmServer.user) {
                        if ((Username).equals(user.Username)) {
                            try {
                                throw new ExistingUserException();
                            } catch (ExistingUserException e) {
                                e.printStackTrace();
                            }
                            isLoggedIn = true;
                            break;
                        }
                    }
                    if (!isLoggedIn) {
                        TmServer.user.add(new ServerUser(Username, Password, null));


                        FileOutputStream Fin = new FileOutputStream("D:\\New.ser");
                        ObjectOutputStream Fout = new ObjectOutputStream(Fin);
                        Fout.writeObject(TmServer.user);
                        Fout.flush();

                        System.out.println("You registered successfully!");
                    }
                } else if (Message.equals("login")) {
                    isLoggedIn = false;
                    Username = InputServer.readUTF();
                    Password = InputServer.readUTF();
                    for (ServerUser user : TmServer.user) {
                        if (Username.equals(user.Username) && (Password.equals(user.Password))) {
                            System.out.println("You have been logged in: " + user.Username);
                            user.Session = new BigInteger("987654321987654321987654321987");
                            user.Session = user.Session.add(x);
                            x = x.add(new BigInteger("1"));
                            isLoggedIn = true;
                            break;
                        }
                    }
                    if (!isLoggedIn)
                        try {
                            throw new WrongPasswordException();
                        } catch (WrongPasswordException e) {
                            e.printStackTrace();
                        }

                } else if (Message.equals("logout")) {
                    isLoggedIn = false;

                    Username = InputServer.readUTF();
                    Session = InputServer.readUTF();

                    for (ServerUser user : TmServer.user)
                        if (Username.equals(user.Username) && (Session.equals(user.Session.toString()))) {
                            System.out.println("Your session logout successfully: " + user.Username);
                            user.Session = null;
                            isLoggedIn = true;
                            break;
                        }
                    if (!isLoggedIn)
                        try {
                            throw new InvalidSessionException();
                        } catch (InvalidSessionException e) {
                            e.printStackTrace();
                        }
                }


            } catch (IOException e) {
                /*StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                System.out.println(errors.toString());*/

                e.toString();
            }
        }
        /*OutputServer.close();
        InputServer.close();*/
    }
}

class ServerUser implements Serializable {
    String Username;
    String Password;
    BigInteger Session;

    public ServerUser(String username, String password, BigInteger session) {
        Username = username;
        Password = password;
        Session = session;
    }
}

//***************************Handling Exceptions***************************//

class WrongPasswordException extends Exception {
    WrongPasswordException() {
        super();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

class InvalidSessionException extends Exception {
    InvalidSessionException() {
        super();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

class ExistingUserException extends Exception {
    ExistingUserException() {
        super();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}