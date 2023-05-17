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

public class LoginActivity extends AppCompatActivity {
    Button activity_login_btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButtonAction();
    }

    public void loginButtonAction(){
        activity_login_btn_login = findViewById(R.id.activity_login_btn_login);
        activity_login_btn_login.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, AdminHomeActivity.class)));
    }
}