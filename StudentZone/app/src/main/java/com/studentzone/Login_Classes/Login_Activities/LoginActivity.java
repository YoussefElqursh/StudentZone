package com.studentzone.Login_Classes.Login_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.studentzone.Admin_Calsses.Admin_Activities.AdminHomeActivity;
import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Activities.StudentHomeActivity;

public class LoginActivity extends AppCompatActivity {
    Button button;
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = findViewById(R.id.activity_login_btn_login);
        button.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, StudentHomeActivity.class)));
    }
}