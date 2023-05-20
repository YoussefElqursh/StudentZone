package com.studentzone.Admin_Calsses.Admin_Models.Admin_Subject_and_AbcenceFile_Model;

public class AdminSubjectModel {

    String SubjectName ,SubjectCode;

    int SubjectIcon;

    public AdminSubjectModel(String subjectName, String subjectCode, int subjectIcon) {
        SubjectName = subjectName;
        SubjectCode = subjectCode;
        SubjectIcon = subjectIcon;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        SubjectCode = subjectCode;
    }

    public int getSubjectIcon() {
        return SubjectIcon;
    }

    public void setSubjectIcon(int subjectIcon) {
        SubjectIcon = subjectIcon;
    }

}
