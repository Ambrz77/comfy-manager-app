package com.example.alireza.taskmanager;

import java.util.ArrayList;

public class User {
    private String Username,Email,Password;
    String Name,Familyname;
    int Type;//Normal/Silver/Gold user//1/2/3
    boolean loggedIn = false;
    ArrayList<Task> tasks = new ArrayList<>();
    ArrayList<Priority> priorities = new ArrayList<>();

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

}
