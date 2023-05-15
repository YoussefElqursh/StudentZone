package com.studentzone.Login_Classes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.Admin_Calsses.AdminHomeActivity;
import com.studentzone.R;

public class LoginActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = findViewById(R.id.activity_login_btn_login);
        button.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, AdminHomeActivity.class)));
    }
}