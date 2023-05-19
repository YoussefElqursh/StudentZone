package com.studentzone.Doctor_Classes.Doctor_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.R;

public class DoctorSubjectPdfsActivity extends AppCompatActivity {
    Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_subject_pdfs);
        buttonBackAction();
    }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_doctor_subject_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(DoctorSubjectPdfsActivity.this, DoctorHomeActivity.class)));
    }
}