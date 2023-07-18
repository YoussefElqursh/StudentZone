package com.studentzone.Student_Classes.Student_Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.studentzone.Admin_Classes.Admin_Activities.AdminHomeActivity;
import com.studentzone.Doctor_Classes.Doctor_Activities.DoctorHomeActivity;
import com.studentzone.Login_Classes.Login_Activities.LoginActivity;
import com.studentzone.R;

public class StudentHomeActivity extends AppCompatActivity {

    CardView cv_subjects_registration, cv_subjects_passed_subjects, cv_subjects;
    Button btn_logout, btn_profile;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    TextView profileName ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        inflate();
        AllCardViewActions();
        logOutConfirmationDialog();
        buttonProfileAction();

    }


    /** Inflate
     **********************************************************************************************/
    public void inflate(){
        cv_subjects_registration = findViewById(R.id.activity_student_home_cv_subjects_registration);
        cv_subjects_passed_subjects = findViewById(R.id.activity_student_home_cv_subjects_passed_subjects);
        cv_subjects = findViewById(R.id.activity_student_home_cv_subjects);
        btn_logout = findViewById(R.id.activity_student_home_btn_logout);
    }

    public void subjectsRegistrationCardViewClickAction(){
        cv_subjects_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomeActivity.this, StudentRegistrationActivity.class));
            }
        });
    }

    public void subjectsPerviousRequestCardViewClickAction(){
        cv_subjects_passed_subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomeActivity.this, StudentPassedSubjectsActivity.class));
            }
        });
    }

    public void subjectsCardViewClickAction(){
        cv_subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomeActivity.this,StudentSubjectActivity.class));
            }
        });
    }

    /**All Card Views Actions
     **********************************************************************************************/
    public void AllCardViewActions(){
        subjectsRegistrationCardViewClickAction();
        subjectsPerviousRequestCardViewClickAction();
        subjectsCardViewClickAction();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(StudentHomeActivity.this);
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

    /**logOut()
     **********************************************************************************************/
    private void logOut(){
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
        btn_profile = findViewById(R.id.activity_student_home_btn_profile);
        profileName = findViewById(R.id.activity_student_home_tv_profileName);


        preferences = getSharedPreferences("userAccount",MODE_PRIVATE);
        String savedUsername = preferences.getString("email", "");
        profileName.setText(savedUsername);
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