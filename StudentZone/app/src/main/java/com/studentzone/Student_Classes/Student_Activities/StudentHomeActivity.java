package com.studentzone.Student_Classes.Student_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.studentzone.R;

public class StudentHomeActivity extends AppCompatActivity {

    CardView activity_student_home_cv_subjects_registration,activity_student_home_cv_subjects_passed_subjects,activity_student_home_cv_subjects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        subjectsRegistrationCardViewAction();
        subjectsPerviousRequestCardViewAction();
        subjectsCardViewAction();
    }

    public void subjectsRegistrationCardViewAction(){
        activity_student_home_cv_subjects_registration = findViewById(R.id.activity_student_home_cv_subjects_registration);
        activity_student_home_cv_subjects_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomeActivity.this, StudentRegistrationActivity.class));
            }
        });
    }

    public void subjectsPerviousRequestCardViewAction(){
        activity_student_home_cv_subjects_passed_subjects = findViewById(R.id.activity_student_home_cv_subjects_passed_subjects);
        activity_student_home_cv_subjects_passed_subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomeActivity.this, StudentPassedSubjectsActivity.class));
            }
        });
    }

    public void subjectsCardViewAction(){
        activity_student_home_cv_subjects = findViewById(R.id.activity_student_home_cv_subjects);
        activity_student_home_cv_subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomeActivity.this,StudentSubjectActivity.class));
            }
        });
    }
}