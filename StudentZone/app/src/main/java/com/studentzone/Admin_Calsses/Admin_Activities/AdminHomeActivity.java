package com.studentzone.Admin_Calsses.Admin_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.studentzone.R;

public class AdminHomeActivity extends AppCompatActivity {
    CardView activity_admin_home_cv_department,activity_admin_home_cv_subjects,activity_admin_home_cv_absence_files,activity_admin_home_cv_doctors_account,activity_admin_home_cv_students_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        departmentsCardViewAction();
        subjectsCardViewAction();
        doctorsCardViewAction();
        studentsCardViewAction();
        absenceFilesCardViewAction();
    }

    public void departmentsCardViewAction() {
        activity_admin_home_cv_department = findViewById(R.id.activity_admin_home_cv_departments);
        activity_admin_home_cv_department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this, AdminDepartmentsActivity.class));
            }
        });
        subjectsCardViewAction();

    }

    public void subjectsCardViewAction() {
        activity_admin_home_cv_subjects = findViewById(R.id.activity_admin_home_cv_subjects);
        activity_admin_home_cv_subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this, AdminSubjectsActivity.class));
            }
        });
    }

    public void doctorsCardViewAction() {
        activity_admin_home_cv_doctors_account = findViewById(R.id.activity_admin_home_cv_doctors_accounts);
        activity_admin_home_cv_doctors_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this,AdminDoctorsAccountsActivity.class));
            }
        });
    }
    public void studentsCardViewAction() {
        activity_admin_home_cv_students_account = findViewById(R.id.activity_admin_home_cv_students_accounts);
        activity_admin_home_cv_students_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this,AdminStudentsAccountsActivity.class));
            }
        });
    }
    public void absenceFilesCardViewAction() {
        activity_admin_home_cv_absence_files = findViewById(R.id.activity_admin_home_cv_absence_files);
        activity_admin_home_cv_absence_files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this,AdminAbsenceFilesActivity.class));
            }
        });
    }


}