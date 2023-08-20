package com.studentzone.Admin_Classes.Admin_Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    private CardView cv_department, cv_subjects, cv_doctors_account, cv_students_account;
    private SharedPreferences preferences;
    private TextView profileName, profileEmail;
    private ImageView profileImage_drawer, profileImage;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private static final int PROFILE_IMAGE_REQUEST_CODE = 1;

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

        View headerView = navigationView.getHeaderView(0);

        profileName = headerView.findViewById(R.id.activity_admin_home_nav_drawer_admin_name);
        profileEmail = headerView.findViewById(R.id.activity_admin_home_nav_drawer_admin_email);
        profileImage_drawer = headerView.findViewById(R.id.activity_admin_home_nav_drawer_shiv_admin_photo);
        profileImage = findViewById(R.id.activity_admin_home_shiv_admin_photo);
    }

    /**
     * fillOutAdminInfo()
     **********************************************************************************************/
    public void fillOutAdminInfo() {


        preferences = getSharedPreferences("userInfo",MODE_PRIVATE);
        String name = preferences.getString("fName", "");
        String email = preferences.getString("email", "");
        String image_uri = preferences.getString("image_uri", "");

        String capitalizedName = name.substring(0, 1).toUpperCase() + name.substring(1);

        profileName.setText(capitalizedName);
        profileEmail.setText(email);
        profileImage_drawer.setImageURI(Uri.parse(image_uri));
        profileImage.setImageURI(Uri.parse(image_uri));

    }

    /* this methode receive edited image from AdminProfileActivity ,
     * to reflect the changes in AdminHomeActivity at the same time
     **********************************************************************************************/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PROFILE_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {

            assert data != null;
            String imageUri = data.getStringExtra("image_uri");

            // Update the image in AdminHomeActivity using the received imageUri
            profileImage_drawer.setImageURI(Uri.parse(imageUri));
            profileImage.setImageURI(Uri.parse(imageUri));

        }
    }

    /**
     * logOut()
     **********************************************************************************************/
    private void logOut() {
        preferences = getSharedPreferences("Login_Prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
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
        Menu menu = navigationView.getMenu();
        navigationView.getHeaderView(0);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
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
    public void openSettings()
    {
        Intent intent = new Intent(getBaseContext(), AdminSettingsActivity.class);
        startActivity(intent);
    }

    public void openContactUS()
    {
        Intent intent = new Intent(getBaseContext(), AdminContactUsActivity.class);
        startActivity(intent);
    }

    public void openProfile()
    {
        Intent intent = new Intent(AdminHomeActivity.this, AdminProfileActivity.class);

        intent.putExtra("request_code", PROFILE_IMAGE_REQUEST_CODE);
        startActivityForResult(intent, PROFILE_IMAGE_REQUEST_CODE);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.activity_admin_home_item_logout:
                logOutConfirmationDialog();
                drawerLayout.closeDrawers();
                break;
            case R.id.activity_admin_home_item_settings:
                openSettings();
                drawerLayout.closeDrawers();
                break;
            case R.id.activity_admin_home_item_profile:
                openProfile();
                drawerLayout.closeDrawers();
                break;
            case R.id.activity_admin_home_item_about_us:
                openContactUS();
                drawerLayout.closeDrawers();
                break;
        }
        return false;
    }


}