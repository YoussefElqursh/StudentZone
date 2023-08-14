package com.studentzone.Admin_Classes.Admin_Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.studentzone.Login_Classes.Login_Activities.LoginActivity;
import com.studentzone.R;

public class   AdminHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    CardView cv_department, cv_subjects, cv_doctors_account, cv_students_account;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    TextView profileImage, profileName, profileEmail;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    View headerView;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    Menu menu;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);


        inflate();
        AllCardViewActions();
        fillOutAdminInfo();
        drawerToggleButtonAction();
    }

    /**
     * Inflate
     **********************************************************************************************/
    public void inflate() {
        cv_department = findViewById(R.id.activity_admin_home_cv_departments);
        cv_subjects = findViewById(R.id.activity_admin_home_cv_subjects);
        cv_doctors_account = findViewById(R.id.activity_admin_home_cv_doctors_accounts);
        cv_students_account = findViewById(R.id.activity_admin_home_cv_students_accounts);
        drawerLayout = findViewById(R.id.activity_admin_home_drl);
        navigationView = findViewById(R.id.activity_admin_home_nav_drawer);
        toolbar = findViewById(R.id.activity_admin_home_tb);

        headerView = navigationView.getHeaderView(0);

        profileName = headerView.findViewById(R.id.activity_admin_home_nav_drawer_admin_name);
        profileEmail = headerView.findViewById(R.id.activity_admin_home_nav_drawer_admin_email);
        profileImage = headerView.findViewById(R.id.activity_admin_home_nav_drawer_admin_an);
    }

    /**
     * fillOutAdminInfo()
     **********************************************************************************************/
    public void fillOutAdminInfo() {


        preferences = getSharedPreferences("userInfo",MODE_PRIVATE);
        String name = preferences.getString("fName", "");
        String email = preferences.getString("email", "");

        String capitalizedStr = name.substring(0, 1).toUpperCase() + name.substring(1);

        String  abbreviation = "";
        String[] words = name.split(" ");
        for (String word : words) {
            char firstLetter = word.charAt(0);
            abbreviation += firstLetter;

            if(abbreviation.length() == 2)
                break;
        }

        profileName.setText(capitalizedStr);
        profileEmail.setText(email);
        profileImage.setText(abbreviation);

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



    public void departmentsCardViewClickAction() {
        cv_department.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), AdminDepartmentsActivity.class)));
    }

    public void subjectsCardViewClickAction() {
        cv_subjects.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), AdminCoursesActivity.class)));
    }

    public void doctorsCardViewClickAction() {
        cv_doctors_account.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), AdminDoctorsAccountsActivity.class)));
    }

    public void studentsCardViewClickAction() {
        cv_students_account.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), AdminStudentsAccountsActivity.class)));
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
     * drawerToggleButtonAction()
     **********************************************************************************************/
    public void drawerToggleButtonAction(){
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


    void logOutConfirmationDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminHomeActivity.this);
        builder.setTitle("Log Out");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("Yes", (dialog, which) -> logOut());
        builder.setNegativeButton("No", null);
        builder.show();

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.activity_admin_home_item_logout :
                logOutConfirmationDialog();
        }
        return false;
    }

}