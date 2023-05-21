package com.studentzone.Admin_Calsses.Admin_Models.Admin_Students_Accounts_Model;

public class AdminStudentAccountModel {

    String StudentName , StudentCode ;

    int StudentIcon ;

    public AdminStudentAccountModel(String studentName, String studentCode, int studentIcon) {
        StudentName = studentName;
        StudentCode = studentCode;
        StudentIcon = studentIcon;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getStudentCode() {
        return StudentCode;
    }

    public void setStudentCode(String studentCode) {
        StudentCode = studentCode;
    }

    public int getStudentIcon() {
        return StudentIcon;
    }

    public void setStudentIcon(int studentIcon) {
        StudentIcon = studentIcon;
    }
}

