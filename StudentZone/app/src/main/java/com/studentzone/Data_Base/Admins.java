package com.studentzone.Data_Base;

public class Admins {

    private int Id;
    private String name;
    private String Email;
    private String Password ;
    private String phoneNumber ;
    private String image_uri ;


    public Admins(String email, String name,  String password, String phoneNumber, String image_uri) {

        this.Email = email;
        this.name = name;
        this.Password = password;
        this.phoneNumber = phoneNumber;
        this.image_uri = image_uri;
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

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
