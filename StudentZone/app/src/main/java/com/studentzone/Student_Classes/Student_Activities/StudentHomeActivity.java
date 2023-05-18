package com.studentzone.Student_Classes.Student_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.studentzone.Login_Classes.Login_Activities.LoginActivity;
import com.studentzone.R;

public class StudentHomeActivity extends AppCompatActivity {

    CardView cv_subjects_registration, cv_subjects_passed_subjects, cv_subjects;
    Button btn_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        subjectsRegistrationCardViewClickAction();
        subjectsPerviousRequestCardViewClickAction();
        subjectsCardViewClickAction();
        buttonLogoutAction();
    }

    public void subjectsRegistrationCardViewClickAction(){
        cv_subjects_registration = findViewById(R.id.activity_student_home_cv_subjects_registration);
        cv_subjects_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomeActivity.this, StudentRegistrationActivity.class));
            }
        });
    }

    public void subjectsPerviousRequestCardViewClickAction(){
        cv_subjects_passed_subjects = findViewById(R.id.activity_student_home_cv_subjects_passed_subjects);
        cv_subjects_passed_subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomeActivity.this, StudentPassedSubjectsActivity.class));
            }
        });
    }

    public void subjectsCardViewClickAction(){
        cv_subjects = findViewById(R.id.activity_student_home_cv_subjects);
        cv_subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomeActivity.this,StudentSubjectActivity.class));
            }
        });
    }

    public void buttonLogoutAction(){
        btn_logout = findViewById(R.id.activity_student_home_btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomeActivity.this, LoginActivity.class));
            }
        });
    }
}