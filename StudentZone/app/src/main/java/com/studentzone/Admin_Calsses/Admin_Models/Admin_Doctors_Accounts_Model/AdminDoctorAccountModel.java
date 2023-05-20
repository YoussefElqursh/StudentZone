package com.studentzone.Admin_Calsses.Admin_Models.Admin_Doctors_Accounts_Model;

public class AdminDoctorAccountModel {

    String DoctorName , DoctorPassword ;

    int DoctorIcon ;

    public AdminDoctorAccountModel(String doctorName, String doctorPassword, int doctorIcon) {
        DoctorName = doctorName;
        DoctorPassword = doctorPassword;
        DoctorIcon = doctorIcon;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getDoctorPassword() {
        return DoctorPassword;
    }

    public void setDoctorPassword(String doctorPassword) {
        DoctorPassword = doctorPassword;
    }

    public int getDoctorIcon() {
        return DoctorIcon;
    }

    public void setDoctorIcon(int doctorIcon) {
        DoctorIcon = doctorIcon;
    }

}
