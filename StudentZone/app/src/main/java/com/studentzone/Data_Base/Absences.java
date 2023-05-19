package com.studentzone.Data_Base;



public class Absences{

    private int id;
    private int student_id;
    private int course_id;
    private String date;
    private String status;

    public Absences(int id, int student_id, int course_id, String date, String status) {
        this.id = id;
        this.student_id = student_id;
        this.course_id = course_id;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
