package com.studentzone.Student_Classes.Student_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.studentzone.R;

public class StudentHomeActivity extends AppCompatActivity {

    CardView registration,PerviousRequest,subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        registration = findViewById(R.id.activity_student_home_cv_registration);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomeActivity.this, StudentRegistrationActivity.class));
            }
        });

        PerviousRequest = findViewById(R.id.activity_student_home_cv_PerviousRequest);
        PerviousRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomeActivity.this, StudentPerviousRequestActivity.class));
            }
        });

        subject = findViewById(R.id.activity_student_home_cv_subject);
        subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomeActivity.this,StudentSubjectActivity.class));
            }
        });
    }
}