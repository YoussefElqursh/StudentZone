package com.studentzone.Doctor_Classes.Doctor_Activities;

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


public class DoctorHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    CardView cv_subjects,cv_assessStudent;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    TextView profileImage, profileName, profileEmail;
    DrawerLayout drawerLayout;
    View headerView;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        inflate();
        fillOutDoctorInfo();
        subjectsCardViewClickAction();
        assessStudentCardViewClickAction();
        drawerToggleButtonAction();

    }

    /** Inflate
     ******************************************************************************************/
    public void inflate(){
        cv_subjects = findViewById(R.id.activity_doctor_home_cv_subjects);
        cv_assessStudent = findViewById(R.id.activity_doctor_home_cv_assessStudent);
        drawerLayout = findViewById(R.id.activity_doctor_home_drl);
        navigationView = findViewById(R.id.activity_doctor_home_nav_drawer);
        toolbar = findViewById(R.id.activity_doctor_home_tb);

        headerView = navigationView.getHeaderView(0);

        profileName = headerView.findViewById(R.id.activity_doctor_home_nav_drawer_doctor_name);
        profileEmail = headerView.findViewById(R.id.activity_doctor_home_nav_drawer_doctor_email);
        profileImage = headerView.findViewById(R.id.activity_doctor_home_nav_drawer_doctor_dn);
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
     * fillOutDoctorInfo()
     **********************************************************************************************/
    public void fillOutDoctorInfo() {


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

    public void subjectsCardViewClickAction() {
        cv_subjects.setOnClickListener(v -> startActivity(new Intent(DoctorHomeActivity.this, DoctorCoursesActivity.class)));
    }

    public void assessStudentCardViewClickAction() {
        cv_assessStudent.setOnClickListener(v -> startActivity(new Intent(DoctorHomeActivity.this, DoctorAssessStudentActivity.class)));
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

    void logoutConfirmation(){
        AlertDialog.Builder builder = new AlertDialog.Builder(DoctorHomeActivity.this);
        builder.setTitle("Log Out");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("Yes", (dialog, which) -> logOut());
        builder.setNegativeButton("No", null);
        builder.show();

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.activity_doctor_home_item_logout :
                logoutConfirmation();
        }
        return false;
    }

}