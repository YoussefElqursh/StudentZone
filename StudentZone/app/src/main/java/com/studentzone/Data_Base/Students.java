package com.studentzone.Data_Base;

import androidx.recyclerview.widget.RecyclerView;

public class Students  {
    private int Id;
    private String Academic_Number ;
    private String FName ;
    private String LastName ;
    private String gender ;
    private String Email;
    private String Password ;

    public Students(int id, String academic_Number, String FName, String gender, String Email, String Password) {

        this.Id = id;
        this.Academic_Number = academic_Number;
        this.FName = FName;
        this.Email = Email;
        this.Password = Password;
        this.gender = gender;
    }

    public Students(int id, String academic_Number, String FName,String Email, String gender) {

        this.Id = id;
        this.Academic_Number = academic_Number;
        this.FName = FName;
        this.Email = Email;
        this.gender = gender;
    }

    public Students(String academic_Number, String FName, String LName,  String gender, String Email, String Password) {

        this.Academic_Number = academic_Number;
        this.FName = FName;
        this.LastName = LName;
        this.gender = gender;
        this.Email = Email;
        this.Password = Password;
    }

    public Students(String FName, String academic_Number, String Email, String Password, String gender) {

        this.FName = FName;
        this.Academic_Number = academic_Number;
        this.Email = Email;
        this.Password = Password;
        this.gender = gender;
    }

    public Students(String FName, String academic_Number) {

        this.Academic_Number = academic_Number;
        this.FName = FName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getAcademic_Number() {
        return Academic_Number;
    }

    public void setAcademic_Number(String academic_Number) {
        Academic_Number = academic_Number;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
