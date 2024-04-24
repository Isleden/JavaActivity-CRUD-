package com.example.login;

public class User {

    private String name;
    private String password;
    private int ID;

    public User(String name,String password,int ID)
    {
        this.name = name;
        this.password = password;
        this.ID = ID;
    }
    public int getId() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }


    public void setId(int id) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
