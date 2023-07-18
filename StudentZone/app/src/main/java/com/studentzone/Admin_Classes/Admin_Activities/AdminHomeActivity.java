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

import com.studentzone.Doctor_Classes.Doctor_Activities.DoctorHomeActivity;
import com.studentzone.Login_Classes.Login_Activities.LoginActivity;
import com.studentzone.R;

public class AdminHomeActivity extends AppCompatActivity {
    CardView cv_department, cv_subjects, cv_doctors_account, cv_students_account;
    Button btn_logout, btn_profile;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    TextView profileName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        inflate();
        AllCardViewActions();
        logOutConfirmationDialog();
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
        btn_logout = findViewById(R.id.activity_admin_home_btn_logout);
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

    /**logOutConfirmationDialog()
     * Shows a confirmation dialog when the user clicks on the log out button.
     * If the user confirms the log out action, call the logOut() method.
     * **********************************************************************************************/
    public void logOutConfirmationDialog() {

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an alert dialog to confirm the log out action
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminHomeActivity.this);
                builder.setTitle("Log Out");
                builder.setMessage("Are you sure you want to log out?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logOut();
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
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



    /**
     * buttonProfileAction()
     **********************************************************************************************/
    public void buttonProfileAction() {
        btn_profile = findViewById(R.id.activity_admin_home_btn_profile);
        profileName = findViewById(R.id.activity_admin_home_tv_profileName);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(profileName.getVisibility() == View.VISIBLE)
                profileName.setVisibility(View.INVISIBLE);
                else
                    profileName.setVisibility(View.VISIBLE);
            }
        });

    }
}