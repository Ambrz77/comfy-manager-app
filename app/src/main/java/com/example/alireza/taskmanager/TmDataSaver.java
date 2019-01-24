package com.example.alireza.taskmanager;

import java.io.Serializable;
import java.math.BigInteger;

public class TmDataSaver implements Serializable {
    private String Username;
    private String Password;
    private BigInteger Session;
    transient private Thread thread;

    public TmDataSaver(String Username, String Password, BigInteger Session) {
        this.Username = Username;
        this.Password = Password;
        this.Session = Session;
        this.thread = new Thread();
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public BigInteger getSession() {
        return Session;
    }

    public void setSession(BigInteger session) {
        Session = session;
    }

    @Override
    public String toString() {
        return "User [Username= " + Username + ", Password= " + Password + ", Session= " + Session + "]";
    }
}
