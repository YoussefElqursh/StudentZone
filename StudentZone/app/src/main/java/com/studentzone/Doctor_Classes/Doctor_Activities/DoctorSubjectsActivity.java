package com.studentzone.Doctor_Classes.Doctor_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.R;

public class DoctorSubjectsActivity extends AppCompatActivity {
    Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_subjects);
        buttonBackAction();
    }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_doctor_subjects_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(DoctorSubjectsActivity.this, DoctorHomeActivity.class)));
    }

}