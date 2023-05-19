package com.studentzone.Doctor_Classes.Doctor_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.studentzone.Login_Classes.Login_Activities.LoginActivity;
import com.studentzone.R;

public class DoctorHomeActivity extends AppCompatActivity {
    CardView cv_subjects;
    Button btn_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
        subjectsCardViewClickAction();
        buttonLogoutAction();
    }

    public void subjectsCardViewClickAction() {
        cv_subjects = findViewById(R.id.activity_doctor_home_cv_subjects);
        cv_subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorHomeActivity.this, DoctorSubjectActivity.class));
            }
        });
    }

    public void buttonLogoutAction(){
        btn_logout = findViewById(R.id.activity_doctor_home_btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorHomeActivity.this, LoginActivity.class));
            }
        });
    }
}