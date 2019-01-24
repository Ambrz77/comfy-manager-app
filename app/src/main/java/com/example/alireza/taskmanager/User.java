package com.example.alireza.taskmanager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.Vector;

public class User {
    private String Username, Email, Password;
    String Name, Familyname;
    int Type;//Normal/Silver/Gold user//1/2/3
    boolean loggedIn = false;
    Vector<Task> tasks = new Vector<>();
    Vector<Priority> priorities = new Vector<>();

    public String getUsername() {
        return Username;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public User(String username, String email, String name, String password, int type) {
        Username = username;
        Email = email;
        Name = name;
        Password = password;
        Type = type;
    }

    public User(String username, String email, String password, String name, String familyname, int type) {
        Username = username;
        Email = email;
        Password = password;
        Name = name;
        Familyname = familyname;
        Type = type;
    }

    void newTask(String subject, Priority priority, int year, int month, int day, int hour, int min, String detail) {
        Task task = new Task(subject, priority);
        task.setTime(year, month, day, hour, min);
        task.setDetail(detail);
        tasks.add(task);
        mergeSortT(tasks);
    }

    void newPriority(String name, int importance) {
        priorities.add(new Priority(name, importance));
        mergeSortP(priorities);
    }

    private Vector<Task> mergeT(final Vector<Task> left, final Vector<Task> right) {
        final Vector<Task> merged = new Vector<>();
        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.get(0).compareTo(right.get(0)) <= 0) {
                merged.add(left.remove(0));
            } else {
                merged.add(right.remove(0));
            }
        }
        merged.addAll(left);
        merged.addAll(right);
        return merged;
    }

    private Vector<Priority> mergeP(final Vector<Priority> left, final Vector<Priority> right) {
        final Vector<Priority> merged = new Vector<>();
        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.get(0).compareTo(right.get(0)) <= 0) {
                merged.add(left.remove(0));
            } else {
                merged.add(right.remove(0));
            }
        }
        merged.addAll(left);
        merged.addAll(right);
        return merged;
    }

    public void mergeSortT(final Vector<Task> input) {
        if (input.size() != 1) {
            final Vector<Task> left = new Vector<>();
            final Vector<Task> right = new Vector<>();
            boolean logicalSwitch = true;
            while (!input.isEmpty()) {
                if (logicalSwitch) {
                    left.add(input.remove(0));
                } else {
                    right.add(input.remove(0));
                }
                logicalSwitch = !logicalSwitch;
            }
            mergeSortT(left);
            mergeSortT(right);
            input.addAll(mergeT(left, right));
        }
    }

    public void mergeSortP(final Vector<Priority> input) {
        if (input.size() != 1) {
            final Vector<Priority> left = new Vector<>();
            final Vector<Priority> right = new Vector<>();
            boolean logicalSwitch = true;
            while (!input.isEmpty()) {
                if (logicalSwitch) {
                    left.add(input.remove(0));
                } else {
                    right.add(input.remove(0));
                }
                logicalSwitch = !logicalSwitch;
            }
            mergeSortP(left);
            mergeSortP(right);
            input.addAll(mergeP(left, right));
        }
    }

    final static int portNumber = 4343;

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        Socket clientSocket = new Socket("localhost", portNumber);
        DataOutputStream OutputClient = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream InputClient = new DataInputStream(clientSocket.getInputStream());
        System.out.println("Client Starts!");
        Thread sendMessage = new Thread(() -> {
            while (true) {
                try {
                    String State, User, Pass, receivedText;
                    receivedText = scan.nextLine();
                    String[] token = receivedText.split(" ", -1);
                    State = token[0];
                    OutputClient.writeUTF(State);
                    OutputClient.flush();
                    User = token[1];
                    OutputClient.writeUTF(User);
                    OutputClient.flush();
                    Pass = token[2];
                    OutputClient.writeUTF(Pass);
                    OutputClient.flush();
                } catch (SocketException s) {
                    break;
                } catch (IOException e) {
                    e.toString();
                }
            }
        });
        Thread readMessage = new Thread(() -> {
            while (true) {
                try {

                    String msg = InputClient.readUTF();
                    System.out.println(msg);
                    System.out.println(InputClient.readUTF());
                    System.out.println(InputClient.readUTF());

                } catch (SocketException s) {
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        sendMessage.start();
        readMessage.start();
    }
}
