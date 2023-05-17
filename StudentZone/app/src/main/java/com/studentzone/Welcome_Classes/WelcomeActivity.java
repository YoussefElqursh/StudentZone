package com.studentzone.Welcome_Classes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.Admin_Calsses.Admin_Activities.AdminDepartmentsActivity;
import com.studentzone.Admin_Calsses.Admin_Activities.AdminHomeActivity;
import com.studentzone.Login_Classes.Login_Activities.LoginActivity;
import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Activities.StudentHomeActivity;

public class WelcomeActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        button = findViewById(R.id.activity_welcome_btn_welcome);
        button.setOnClickListener(v -> startActivity(new Intent(WelcomeActivity.this, LoginActivity.class)));
    }
}