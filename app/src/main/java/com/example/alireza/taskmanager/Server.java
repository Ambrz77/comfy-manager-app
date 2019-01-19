package com.example.alireza.taskmanager;

import java.util.ArrayList;

class ExistedUserException extends Exception {
    public ExistedUserException(String message) {
        super(message);
    }
}

public class Server {
    ArrayList<User> users = new ArrayList<>();

    public void Register(String u, String e, String n, String p, int t) {
        users.add(new User(u, e, n, p, t));
    }

    public void Register(String u, String e, String n, String p, String f, int t) {
        users.add(new User(u, e, n, p, f, t));

    }

    public void AddPriority(User u, String n, int i) { u.priorities.add(new Priority(n, i)); }

    public void AddTask(User u, String s, Priority p) { u.tasks.add(new Task(s, p)); }

    public int isUser(String n) {
        for (User u:users) {
            if (u.getUsername().equals(n)) {
                return 1;
            }
            if (u.getEmail().equals(n)) {
                return 2;
            }
        }
        return 0;
    }

    private User getUser(String n) {
        for (User u :
                users) {
            if (u.getEmail().equals(n) || u.getUsername().equals(n)) {
                return u;
            }
        }
        return null;
    }

    public void login(String ne, String p) {
        if (isUser(ne) > 0) {
            User u = getUser(ne);
            if (p.equals(u.getPassword())) {
                u.loggedIn = true;
                System.out.println("logged in!");
            } else {
                System.out.println("wrong pass!");
            }
        } else {
            System.out.println("this user is not registered!");
        }
    }
}
