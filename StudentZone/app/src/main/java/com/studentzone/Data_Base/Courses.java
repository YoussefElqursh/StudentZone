package com.studentzone.Data_Base;

public class Courses {


    private int id;
    private String code;
    private String name;
    private int preRequest;
    private int department;
    private int doctor;


    public Courses(int id, String code, String name, int preRequest, int department, int doctor) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.preRequest = preRequest;
        this.doctor = doctor;
        this.department = department;
    }

    public Courses(String name, String code, int department, int doctor, int preRequest)
    {
        this.name = name;
        this.code = code;
        this.department = department;
        this.doctor = doctor;
        this.preRequest = preRequest;

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

    public int getPreRequest() {
        return preRequest;
    }

    public void setPreRequest(int preRequest) {
        this.preRequest = preRequest;
    }

    public int getDepartment() {return department;}

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getDoctor() {
        return doctor;
    }

    public void setDoctor(int doctor) {
        this.doctor = doctor;
    }
}
