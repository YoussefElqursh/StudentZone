package com.studentzone.Student_Classes.Student_Models.RegestrationModel;

import android.widget.CheckBox;

public class  SubjectRegestrationModel {

    String subject_name, subject_code;
    boolean checkBox;

    public SubjectRegestrationModel(String subject_name) {
        this.subject_name = subject_name;

    }


    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }
}

