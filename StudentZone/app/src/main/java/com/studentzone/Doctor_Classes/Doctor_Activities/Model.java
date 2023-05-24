package com.studentzone.Doctor_Classes.Doctor_Activities;

public class Model {
    int image;
    String SubjectName,CodeName;

    public Model(int image, String subjectName, String codeName) {
        this.image = image;
        SubjectName = subjectName;
        CodeName = codeName;
    }
    public Model(String subjectName, String codeName) {
        
        SubjectName = subjectName;
        CodeName = codeName;
    }
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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
