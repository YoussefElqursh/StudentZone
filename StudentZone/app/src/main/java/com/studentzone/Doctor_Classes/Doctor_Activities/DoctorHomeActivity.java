package com.studentzone.Doctor_Classes.Doctor_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.studentzone.Login_Classes.Login_Activities.LoginActivity;
import com.studentzone.R;

public class DoctorHomeActivity extends AppCompatActivity {
    CardView cv_subjects;
    Button btn_logout, btn_profile;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    TextView profileName ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        inflate();
        subjectsCardViewClickAction();
        buttonLogoutAction();
        buttonProfileAction();
    }

    /** Inflate
     ******************************************************************************************/
    public void inflate(){
        cv_subjects = findViewById(R.id.activity_doctor_home_cv_subjects);
        btn_logout = findViewById(R.id.activity_doctor_home_btn_logout);

    }


    public void subjectsCardViewClickAction() {
        cv_subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorHomeActivity.this, DoctorSubjectsActivity.class));

            }
        });
    }

    /**buttonLogoutAction
     **********************************************************************************************/
    public void buttonLogoutAction(){
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
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
        btn_profile = findViewById(R.id.activity_doctor_home_btn_profile);
        profileName = findViewById(R.id.activity_doctor_home_tv_profileName);
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