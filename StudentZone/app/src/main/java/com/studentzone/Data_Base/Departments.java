package com.studentzone.Data_Base;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class Departments {
    private int id;
    private int code;
    private String name;

    public Departments(int id, String name,int code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }
    public Departments(String name,int code) {
        this.name = name;
        this.code = code;
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

}
