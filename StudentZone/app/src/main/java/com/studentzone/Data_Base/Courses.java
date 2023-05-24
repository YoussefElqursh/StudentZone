package com.studentzone.Data_Base;

public class Courses {


    private int id;
    private String code;
    private String name;
    private String preRequest_name;
    private String department;
    private String doctor;


    public Courses(int id, String code, String name, String preRequest_name, String department, String doctor) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.preRequest_name = preRequest_name;
        this.doctor = doctor;
        this.department = department;
    }

    public Courses(String name, String code, String department, String doctor, String previousSubject)
    {
        this.name = name;
        this.code = code;
        this.department = department;
        this.doctor = doctor;
        this.preRequest_name = previousSubject;

    }

    public Courses(String name, String code) {
        this.name = name;
        this.code = code;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreRequest_name() {
        return preRequest_name;
    }

    public void setPreRequest_name(String preRequest_name) {
        this.preRequest_name = preRequest_name;
    }

    public String getDepartment() {return department;}

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
