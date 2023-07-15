package com.studentzone.Admin_Classes.Admin_Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.studentzone.Login_Classes.Login_Activities.LoginActivity;
import com.studentzone.R;

public class AdminHomeActivity extends AppCompatActivity {
    CardView cv_department, cv_subjects, cv_doctors_account, cv_students_account, cv_absence_files;
    Button btn_logout, btn_profile;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        inflate();
        AllCardViewActions();
        buttonLogoutAction();
        buttonProfileAction();

    }

    /**
     * Inflate
     **********************************************************************************************/
    public void inflate() {
        cv_department = findViewById(R.id.activity_admin_home_cv_departments);
        cv_subjects = findViewById(R.id.activity_admin_home_cv_subjects);
        cv_doctors_account = findViewById(R.id.activity_admin_home_cv_doctors_accounts);
        cv_students_account = findViewById(R.id.activity_admin_home_cv_students_accounts);
    }


    public void departmentsCardViewClickAction() {
        cv_department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), AdminDepartmentsActivity.class));
            }
        });
    }

    public void subjectsCardViewClickAction() {
        cv_subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), AdminSubjectsActivity.class));
            }
        });
    }

    public void doctorsCardViewClickAction() {
        cv_doctors_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), AdminDoctorsAccountsActivity.class));
            }
        });
    }

    public void studentsCardViewClickAction() {
        cv_students_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), AdminStudentsAccountsActivity.class));
            }
        });
    }

    /**
     * All Card Views Actions
     **********************************************************************************************/
    public void AllCardViewActions() {
        departmentsCardViewClickAction();
        subjectsCardViewClickAction();
        studentsCardViewClickAction();
        doctorsCardViewClickAction();
    }

    /**
     * buttonLogoutAction
     **********************************************************************************************/
    public void buttonLogoutAction() {
        btn_logout = findViewById(R.id.activity_admin_home_btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Show a confirmation dialog
                new AlertDialog.Builder(AdminHomeActivity.this)
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to logout?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // User clicked Yes, so log out and go to login activity
                                logOut();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        });
            }

    /**
     * logOut()
     **********************************************************************************************/
    private void logOut() {
        preferences = getSharedPreferences("Login_Prefs", MODE_PRIVATE);
        editor = preferences.edit();
        editor.clear();
        editor.apply();
        startActivity(new Intent(getBaseContext(), LoginActivity.class));
        finish();

    }

    public void buttonProfileAction() {
        btn_profile = findViewById(R.id.activity_admin_home_btn_profile);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Hi,joo", Toast.LENGTH_SHORT).show();
            }
        });

    }
}