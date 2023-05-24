package com.studentzone.Doctor_Classes.Doctor_Model;

public class DoctorSubjectModel {

    String SubjectName ,StudentCode;

    int SubjectIcon ;

    public DoctorSubjectModel(String subjectName, String studentCode, int subjectIcon) {
        SubjectName = subjectName;
        StudentCode = studentCode;
        SubjectIcon = subjectIcon;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

    public String getStudentCode() {
        return StudentCode;
    }

    public void setStudentCode(String studentCode) {
        StudentCode = studentCode;
    }

    public int getSubjectIcon() {
        return SubjectIcon;
    }

    public void setSubjectIcon(int subjectIcon) {
        SubjectIcon = subjectIcon;
    }
}
