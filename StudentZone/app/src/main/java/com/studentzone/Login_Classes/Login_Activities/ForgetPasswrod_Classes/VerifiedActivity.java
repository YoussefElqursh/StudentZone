package com.studentzone.Login_Classes.Login_Activities.ForgetPasswrod_Classes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.Login_Classes.Login_Activities.LoginActivity;
import com.studentzone.R;

public class VerifiedActivity extends AppCompatActivity {

    Button verified ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verified);

        verified = findViewById(R.id.btn_verified);

        Verified();
    }

    public void Verified()
    {
        verified.setOnClickListener(v -> {
            Intent intent =new Intent(VerifiedActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}