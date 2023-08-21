package com.studentzone.Student_Classes.Student_Models.SubjectModel;

public class SubjectModel {

    String SubjectName,CodeName;


    public SubjectModel(String SubjectName, String CodeName) {
        this.SubjectName = SubjectName;
        this.CodeName = CodeName;

    }

    public SubjectModel(String courseName) {

    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

    public String getCodeName() {
        return CodeName;
    }

    public void setCodeName(String codeName) {
        CodeName = codeName;
    }


}
