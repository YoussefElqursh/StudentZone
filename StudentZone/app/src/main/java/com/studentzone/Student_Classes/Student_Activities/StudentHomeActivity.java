package com.studentzone.Student_Classes.Student_Activities;

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

public class StudentHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private CardView cv_subjects_registration, cv_subjects_passed_subjects, cv_subjects;
    private SharedPreferences preferences;
    private TextView  profileName, profileEmail;
    private ImageView profileImage_drawer,profileImage;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private static final int PROFILE_IMAGE_REQUEST_CODE = 1;
    private Intent intent_send;

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

        View headerView = navigationView.getHeaderView(0);

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

    }

    /* this methode receive edited image from StudentProfileActivity ,
    *  to reflect the changes in StudentHomeActivity at the same time
     **********************************************************************************************/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PROFILE_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {

            assert data != null;
            String imageUri = data.getStringExtra("image_uri");

            // Update the image in StudentHomeActivity using the received imageUri
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
        Menu menu = navigationView.getMenu();
        navigationView.getHeaderView(0);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
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
        Intent intent = new Intent(getBaseContext(), StudentSettingsActivity.class);
        startActivity(intent);
    }

    public void openContactUS()
    {
        Intent intent = new Intent(getBaseContext(), StudentContactUsActivity.class);
        startActivity(intent);
    }

    public void openProfile()
    {
        Intent intent = new Intent(getBaseContext(), StudentProfileActivity.class);

        intent.putExtra("request_code",PROFILE_IMAGE_REQUEST_CODE);
        startActivityForResult(intent,PROFILE_IMAGE_REQUEST_CODE);
    }

    /** openShareApplication()
     *  Open Share Application
     **********************************************************************************************/
    public void openShareApplication() {
        intent_send = new Intent(Intent.ACTION_SEND);;
        intent_send.setType("text/plain");
        intent_send.putExtra(Intent.EXTRA_SUBJECT, "");
        intent_send.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.student.zone");
        startActivity(Intent.createChooser(intent_send, "Share via"));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.activity_student_home_item_logout :
                logOutConfirmation();
                drawerLayout.closeDrawers();
                break;
            case R.id.activity_student_home_item_settings:
                drawerLayout.closeDrawers();
                openSettings();
                break;
            case R.id.activity_student_home_item_profile:
                openProfile();
                drawerLayout.closeDrawers();
                break;
            case R.id.activity_student_home_item_about_us:
                openContactUS();
                drawerLayout.closeDrawers();
                break;
            case R.id.activity_student_home_item_share:
                openShareApplication();
                drawerLayout.closeDrawers();
                break;
        }
        return false;
    }
}


