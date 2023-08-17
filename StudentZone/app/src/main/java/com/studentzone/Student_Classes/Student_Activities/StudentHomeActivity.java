package com.studentzone.Student_Classes.Student_Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.studentzone.Admin_Classes.Admin_Activities.AdminSettingsActivity;
import com.studentzone.Login_Classes.Login_Activities.LoginActivity;
import com.studentzone.R;

public class    StudentHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    CardView cv_subjects_registration, cv_subjects_passed_subjects, cv_subjects;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    TextView  profileName, profileEmail;
    ImageView profileImage_drawer,profileImage;
    View headerView;
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
        fillOutStudentInfo();
        AllCardViewActions();
        drawerToggleButtonAction();
    }


    /**
     * Inflate
     **********************************************************************************************/
    public void inflate() {
        cv_subjects_registration = findViewById(R.id.activity_student_home_cv_subjects_registration);
        cv_subjects_passed_subjects = findViewById(R.id.activity_student_home_cv_subjects_passed_subjects);
        cv_subjects = findViewById(R.id.activity_student_home_cv_subjects);
        drawerLayout = findViewById(R.id.activity_student_home_drl);
        navigationView = findViewById(R.id.activity_student_home_nav_drawer);
        toolbar = findViewById(R.id.activity_student_home_tb);

        headerView = navigationView.getHeaderView(0);

        profileImage_drawer = headerView.findViewById(R.id.activity_student_home_nav_drawer_shiv_student_photo);
        profileImage = findViewById(R.id.activity_student_home_shiv_student_photo);
        profileName = headerView.findViewById(R.id.activity_student_home_nav_drawer_student_name);
        profileEmail = headerView.findViewById(R.id.activity_student_home_nav_drawer_student_email);
    }

    /**
     * fillOutStudentInfo()
     **********************************************************************************************/
    public void fillOutStudentInfo() {


        preferences = getSharedPreferences("userInfo",MODE_PRIVATE);
        String name = preferences.getString("fName", "");
        String email = preferences.getString("email", "");
        String image_uri = preferences.getString("image_uri", "");

        String capitalizedStr = name.substring(0, 1).toUpperCase() + name.substring(1);

        profileName.setText(capitalizedStr);
        profileEmail.setText(email);
        profileImage_drawer.setImageURI(Uri.parse(image_uri));
        profileImage.setImageURI(Uri.parse(image_uri));


//        String  abbreviation = "";
//        String[] words = name.split(" ");
//        for (String word : words) {
//            char firstLetter = word.charAt(0);
//            abbreviation += firstLetter;
//
//            if(abbreviation.length() == 2)
//                break;
//        }

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

    public void subjectsRegistrationCardViewClickAction() {
        cv_subjects_registration.setOnClickListener(v -> startActivity(new Intent(StudentHomeActivity.this, StudentRegistrationActivity.class)));
    }

    public void subjectsPreviousRequestCardViewClickAction() {
        cv_subjects_passed_subjects.setOnClickListener(v -> startActivity(new Intent(StudentHomeActivity.this, StudentGradesActivity.class)));
    }



    public void subjectsCardViewClickAction() {
        cv_subjects.setOnClickListener(v -> startActivity(new Intent(StudentHomeActivity.this, StudentSubjectActivity.class)));
    }


    /**
     * All Card Views Actions
     **********************************************************************************************/
    public void AllCardViewActions() {
        subjectsRegistrationCardViewClickAction();
        subjectsPreviousRequestCardViewClickAction();
        subjectsCardViewClickAction();
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
    void logOutConfirmation(){
        AlertDialog.Builder builder = new AlertDialog.Builder(StudentHomeActivity.this);
        builder.setTitle("Log Out");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("Yes", (dialog, which) -> logOut());
        builder.setNegativeButton("No", null);
        builder.show();

    }

    public void openSettings()
    {
        Intent intent = new Intent(getBaseContext(), AdminSettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.activity_student_home_item_logout :
                logOutConfirmation();
                break;
            case R.id.activity_student_home_item_settings:
                openSettings();
                break;
            case R.id.activity_student_home_item_profile:
                startActivity(new Intent(getBaseContext(), StudentProfileActivity.class));
                break;
        }
        return false;
    }

}


