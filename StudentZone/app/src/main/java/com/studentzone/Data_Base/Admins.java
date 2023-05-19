package com.studentzone.Data_Base;

public class Admins {

    private int Id;
    private String name;
    private String Email;
    private String Password ;

    public Admins(int id, String name, String email, String password) {
        Id = id;
        this.name = name;
        Email = email;
        Password = password;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
