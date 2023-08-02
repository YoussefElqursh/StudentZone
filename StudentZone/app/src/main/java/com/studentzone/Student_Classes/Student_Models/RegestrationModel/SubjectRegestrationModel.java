package com.studentzone.Student_Classes.Student_Models.RegestrationModel;

public class SubjectRegestrationModel {

    String SubjectName,CodeName;



    boolean checkBox;

    public SubjectRegestrationModel(String subjectName, String codeName,boolean checkBox) {
        SubjectName = subjectName;
        CodeName = codeName;
        this.checkBox=checkBox;

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

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }



}
