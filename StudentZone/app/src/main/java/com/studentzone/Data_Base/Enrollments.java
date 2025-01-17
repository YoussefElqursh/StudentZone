package com.studentzone.Data_Base;

public class Enrollments {

    private int id;
    private int student_id;
    private int course_id;
    private int student_degree;
    private String student_grade;

    public Enrollments(int student_degree, String student_grade) {

        this.student_degree = student_degree;
        this.student_grade = student_grade;

    }

    public Enrollments(int student_id, int course_id, int student_degree, String student_grade) {

        this.student_id = student_id;
        this.course_id = course_id;
        this.student_degree = student_degree;
        this.student_grade = student_grade;

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

    public int getCourse_id() {
        return course_id;
    }

    public int getStudent_degree() {
        return student_degree;
    }

    public String getStudent_grade() {
        return student_grade;
    }

}
