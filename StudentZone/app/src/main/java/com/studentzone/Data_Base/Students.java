package com.studentzone.Data_Base;

public class Students  {


    private int Id;
    private int Academic_Number ;
    private String FName ;
    private String LastName ;
    private String gender ;
    private String Email;
    private String Password ;

    public Students(int id, int academic_Number, String FName, String LName,  String gender, String Email, String Password) {

        this.Id = id;
        this.Academic_Number = academic_Number;
        this.FName = FName;
        this.LastName = LName;
        this.Email = Email;
        this.Password = Password;
        this.gender = gender;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getAcademic_Number() {
        return Academic_Number;
    }

    public void setAcademic_Number(int academic_Number) {
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


