package com.studentzone.Data_Base;

public class Courses {


    private int id;
    private int code;
    private String name;
    private String preRequest_name;
    private int department_id;
    private int doctor_id;


    public Courses(int id, int code, String name, String preRequest_name, int department_id, int doctor_id) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.preRequest_name = preRequest_name;
        this.doctor_id = doctor_id;
        this.department_id = department_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }
}
