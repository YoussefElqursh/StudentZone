package com.studentzone.Data_Base;


public class Departments {
    private int id;
    private String code;
    private String name;

    public Departments(int id, String name,String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }
    public Departments(String name,String code) {
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


}
