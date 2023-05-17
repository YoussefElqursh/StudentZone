package com.studentzone.Doctor_Classes.Doctor_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.Admin_Calsses.Admin_Activities.AdminDepartmentsActivity;
import com.studentzone.Admin_Calsses.Admin_Activities.AdminHomeActivity;
import com.studentzone.R;

public class DoctorSubjectPdfsActivity extends AppCompatActivity {
    Button activity_admin_doctor_subject_pdfs_btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_subject_pdfs);
    }

//    public void backButtonAction(){
//        activity_admin_doctor_subject_pdfs_btn_back = findViewById(R.id.activity_admin_doctor_subject_pdfs_btn_back);
//        activity_admin_doctor_subject_pdfs_btn_back.setOnClickListener(v -> startActivity(new Intent(DoctorSubjectPdfsActivity.this, )));
//    }
}