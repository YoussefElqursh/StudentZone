package com.studentzone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        button = findViewById(R.id.activity_welcome_btn_welcome);
        button.setOnClickListener(v -> startActivity(new Intent(WelcomeActivity.this,AdminHomeActivity.class)));
    }
}