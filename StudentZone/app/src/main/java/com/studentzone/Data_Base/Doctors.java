package com.studentzone.Data_Base;

public class Doctors {

    private int id ;
    private String FName ;
    private String LName;
    private String gender ;
    private String Phone ;
    private String Email ;
    private String Password ;
    private String image_uri ;


    public Doctors(String FName, String email, String password, String gender, String phone, String image_uri)   {
        this.FName = FName;
        this.Email = email;
        this.Password = password;
        this.gender = gender;
        this.Phone = phone;
        this.image_uri = image_uri;
    }

    public Doctors(String FName, String email, String password, String gender, String phone)   {
        this.FName = FName;
        this.Email = email;
        this.Password = password;
        this.gender = gender;
        this.Phone = phone;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {return Phone;}

    public void setPhone(String phone) {
        Phone = phone;
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
}
