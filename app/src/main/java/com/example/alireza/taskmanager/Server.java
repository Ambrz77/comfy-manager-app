package com.example.alireza.taskmanager;

import java.util.ArrayList;

public class Server {
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Task> tasks = new ArrayList<>();
    ArrayList<Priority> priorities = new ArrayList<>();

    public void AddUser(String u, String e, String n, String p, int t) {
        users.add(new User(u, e, n, p, t));
    }

    public void AddUser(String u, String e, String n, String p, String f, int t) {
        users.add(new User(u, e, n, p, f, t));

    }

    public void AddPriority(String n, int i) {
        priorities.add(new Priority(n, i));
    }

    public void AddTask(String s, Priority p) {
        tasks.add(new Task(s, p));
    }
}
