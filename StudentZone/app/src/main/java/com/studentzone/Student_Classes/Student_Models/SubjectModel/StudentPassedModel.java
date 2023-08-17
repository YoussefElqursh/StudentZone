package com.studentzone.Student_Classes.Student_Models.SubjectModel;

public class StudentPassedModel {
    String SubjectName,grade;
    int SubjectScore;
    public StudentPassedModel(String subjectName, int Score,String grade) {
        SubjectName = subjectName;
        SubjectScore = Score;
        this.grade=grade;

    }
    public String getSubjectName() {
        return SubjectName;
    }
    public int getSubjectScore() {
        return SubjectScore;
    }
    public String getgrade() {
        return grade;
    }
    public void setSubjectName(String subjectName) {
        this.SubjectName = subjectName;
    }
    public void setSubjectScore(int subjectScore) {
        this.SubjectScore = subjectScore;
    }
    public void setSubjectgrade(String subjectgrade) {
        this.grade = subjectgrade;
    }


}
