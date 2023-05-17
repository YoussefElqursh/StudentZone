package com.studentzone.Student_Classes.Student_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.Admin_Calsses.Admin_Activities.AdminDepartmentsActivity;
import com.studentzone.Admin_Calsses.Admin_Activities.AdminHomeActivity;
import com.studentzone.R;

public class StudentRegistrationActivity extends AppCompatActivity {
    Button activity_student_regestration_btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        backButtonAction();
    }

    public void backButtonAction(){
        activity_student_regestration_btn_back = findViewById(R.id.activity_student_regestration_btn_back);
        activity_student_regestration_btn_back.setOnClickListener(v -> startActivity(new Intent(StudentRegistrationActivity.this, StudentHomeActivity.class)));
    }
}