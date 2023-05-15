package com.studentzone.Admin_Calsses.Admin_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.studentzone.R;

public class AdminHomeActivity extends AppCompatActivity {
    CardView departments,add_subject,absent_files,add_doctor_account,add_student_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        departments = findViewById(R.id.activity_admin_home_cv_departments);
        departments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this, AdminDepartmentsActivity.class));
            }
        });

        add_subject = findViewById(R.id.activity_admin_home_cv_subjects);
        add_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this, AdminSubjectsActivity.class));
            }
        });

        absent_files = findViewById(R.id.activity_admin_home_cv_doctors_accounts);
        absent_files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this,AdminDoctorsAccountsActivity.class));
            }
        });

        add_doctor_account = findViewById(R.id.activity_admin_home_cv_students_accounts);
        add_doctor_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this,AdminStudentsAccountsActivity.class));
            }
        });

        add_student_account = findViewById(R.id.activity_admin_home_cv_absence_files);
        add_student_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this,AdminAbsenceFilesActivity.class));
            }
        });
    }
}