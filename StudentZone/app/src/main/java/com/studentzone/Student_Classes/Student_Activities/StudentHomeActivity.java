package com.studentzone.Student_Classes.Student_Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.studentzone.Admin_Classes.Admin_Activities.AdminHomeActivity;
import com.studentzone.Doctor_Classes.Doctor_Activities.DoctorHomeActivity;
import com.studentzone.Login_Classes.Login_Activities.LoginActivity;
import com.studentzone.R;



public class StudentHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    CardView cv_subjects_registration, cv_subjects_passed_subjects, cv_subjects;
    Button btn_logout, btn_profile;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    TextView profileName;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        inflate();
        AllCardViewActions();
//        logOutConfirmationDialog();
//        buttonProfileAction();
        drawerToggleButtonAction();
    }


    /**
     * Inflate
     **********************************************************************************************/
    public void inflate() {
        cv_subjects_registration = findViewById(R.id.activity_student_home_cv_subjects_registration);
        cv_subjects_passed_subjects = findViewById(R.id.activity_student_home_cv_subjects_passed_subjects);
        cv_subjects = findViewById(R.id.activity_student_home_cv_subjects);
//        btn_logout = findViewById(R.id.activity_student_home_btn_logout);
        drawerLayout = findViewById(R.id.activity_student_home_drl);
        navigationView = findViewById(R.id.activity_student_home_nav_drawer);
        toolbar = findViewById(R.id.activity_student_home_tb);
    }

    public void subjectsRegistrationCardViewClickAction() {
        cv_subjects_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomeActivity.this, StudentRegistrationActivity.class));
            }
        });
    }

    public void subjectsPerviousRequestCardViewClickAction() {
        cv_subjects_passed_subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomeActivity.this, StudentPassedSubjectsActivity.class));
            }
        });
    }



    public void subjectsCardViewClickAction() {
        cv_subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomeActivity.this, StudentSubjectActivity.class));
            }
        });
    }

    /**
     * All Card Views Actions
     **********************************************************************************************/
    public void AllCardViewActions() {
        subjectsRegistrationCardViewClickAction();
        subjectsPerviousRequestCardViewClickAction();
        subjectsCardViewClickAction();
    }

    /**
     * logOutConfirmationDialog()
     * Shows a confirmation dialog when the user clicks on the log out button.
     * If the user confirms the log out action, call the logOut() method.
     **********************************************************************************************/
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
//        btn_profile = findViewById(R.id.activity_student_home_btn_profile);
//        profileName = findViewById(R.id.activity_student_home_tv_profileName);


        preferences = getSharedPreferences("userName", MODE_PRIVATE);
        String name = preferences.getString("fName", "");

        String capitalizedStr = name.substring(0, 1).toUpperCase() + name.substring(1);
        profileName.setText(capitalizedStr);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (profileName.getVisibility() == View.VISIBLE)
                    profileName.setVisibility(View.INVISIBLE);
                else
                    profileName.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * drawerToggleButtonAction()
     **********************************************************************************************/
    public void drawerToggleButtonAction() {
        setSupportActionBar(toolbar);
        menu = navigationView.getMenu();
        navigationView.getHeaderView(0);
        navigationView.bringToFront();
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setHomeButtonEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);


    }
    void logoutconfirmation2(){
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.activity_student_home_item_logout :
                logoutconfirmation2();
        }
        return false;
    }




}


