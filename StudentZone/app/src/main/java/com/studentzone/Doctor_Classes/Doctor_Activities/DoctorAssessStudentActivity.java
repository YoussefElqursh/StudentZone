package com.studentzone.Doctor_Classes.Doctor_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.Admin_Classes.Admin_Activities.AdminHomeActivity;
import com.studentzone.Admin_Classes.Admin_Activities.AdminSubjectsActivity;
import com.studentzone.Admin_Classes.Admin_Models.SubjectRecyclerViewAdapter;
import com.studentzone.Data_Base.Courses;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;

import java.util.ArrayList;

public class DoctorAssessStudentActivity extends AppCompatActivity {

    Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_assess_student);

        buttonBackAction();
    }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_doctor_assessStudent_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(DoctorAssessStudentActivity.this, DoctorHomeActivity.class)));
    }
}