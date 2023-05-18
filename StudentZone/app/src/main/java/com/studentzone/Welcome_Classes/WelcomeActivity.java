package com.studentzone.Welcome_Classes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.Admin_Calsses.Admin_Activities.AdminHomeActivity;
import com.studentzone.Doctor_Classes.Doctor_Activities.DoctorHomeActivity;
import com.studentzone.Doctor_Classes.Doctor_Activities.DoctorSubjectPdfsActivity;
import com.studentzone.Login_Classes.Login_Activities.LoginActivity;
import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Activities.StudentHomeActivity;

public class WelcomeActivity extends AppCompatActivity {
    Button btn_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        buttonWelcomeAction();
    }

    public void buttonWelcomeAction(){
        btn_welcome = findViewById(R.id.activity_welcome_btn_welcome);
        btn_welcome.setOnClickListener(v -> startActivity(new Intent(WelcomeActivity.this, AdminHomeActivity.class)));
    }
}