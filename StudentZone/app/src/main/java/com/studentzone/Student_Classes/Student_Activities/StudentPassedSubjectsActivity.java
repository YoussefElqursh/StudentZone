package com.studentzone.Student_Classes.Student_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.R;

public class StudentPassedSubjectsActivity extends AppCompatActivity {

    Button activity_admin_student_passed_subjects_btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_passed_subjects);
        backButtonAction();
    }

    public void backButtonAction(){
        activity_admin_student_passed_subjects_btn_back = findViewById(R.id.activity_admin_student_passed_subjects_btn_back);
        activity_admin_student_passed_subjects_btn_back.setOnClickListener(v -> startActivity(new Intent(StudentPassedSubjectsActivity.this, StudentHomeActivity.class)));
    }
}