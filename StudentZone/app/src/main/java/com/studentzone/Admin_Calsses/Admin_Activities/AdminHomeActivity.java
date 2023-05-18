package com.studentzone.Admin_Calsses.Admin_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.studentzone.Login_Classes.Login_Activities.LoginActivity;
import com.studentzone.R;

public class AdminHomeActivity extends AppCompatActivity {
    CardView cv_department, cv_subjects, cv_doctors_account, cv_students_account, cv_absence_files;
    Button btn_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        AllCardViewActions();
        buttonLogoutAction();
    }

    public void departmentsCardViewClickAction() {
        cv_department = findViewById(R.id.activity_admin_home_cv_departments);
        cv_department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this, AdminDepartmentsActivity.class));
            }
        });
    }

    public void subjectsCardViewClickAction() {
        cv_subjects = findViewById(R.id.activity_admin_home_cv_subjects);
        cv_subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this, AdminSubjectsActivity.class));
            }
        });
    }

    public void doctorsCardViewClickAction() {
        cv_doctors_account = findViewById(R.id.activity_admin_home_cv_doctors_accounts);
        cv_doctors_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this,AdminDoctorsAccountsActivity.class));
            }
        });
    }
    public void studentsCardViewClickAction() {
        cv_students_account = findViewById(R.id.activity_admin_home_cv_students_accounts);
        cv_students_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this,AdminStudentsAccountsActivity.class));
            }
        });
    }
    public void absenceFilesCardViewClickAction() {
        cv_absence_files = findViewById(R.id.activity_admin_home_cv_absence_files);
        cv_absence_files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this,AdminAbsenceFilesActivity.class));
            }
        });
    }


    /**All Card Views Actions
     **********************************************************************************************/
    public void AllCardViewActions(){
        departmentsCardViewClickAction();
        subjectsCardViewClickAction();
        doctorsCardViewClickAction();
        studentsCardViewClickAction();
        absenceFilesCardViewClickAction();
    }

    public void buttonLogoutAction(){
        btn_logout = findViewById(R.id.activity_admin_home_btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this, LoginActivity.class));
            }
        });
    }



}