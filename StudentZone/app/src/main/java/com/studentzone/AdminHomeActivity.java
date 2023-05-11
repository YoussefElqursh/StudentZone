package com.studentzone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminHomeActivity extends AppCompatActivity {
    CardView add_department,add_subject,absent_files,add_doctor_account,add_student_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        add_department = findViewById(R.id.activity_system_adminstrator_cv_add_department);
        add_department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this, AdminDepartmentsActivity.class));
            }
        });

        add_subject = findViewById(R.id.activity_system_adminstrator_cv_add_subject);
        add_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this, AdminSubjectsActivity.class));
            }
        });

        absent_files = findViewById(R.id.activity_system_adminstrator_cv_absent_files);
        absent_files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this,LoginActivity.class));
            }
        });

        add_doctor_account = findViewById(R.id.activity_system_adminstrator_cv_add_doctor_account);
        add_doctor_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this,LoginActivity.class));
            }
        });

        add_student_account = findViewById(R.id.activity_system_adminstrator_cv_add_student_account);
        add_student_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this,LoginActivity.class));
            }
        });
    }
}