package com.studentzone.Doctor_Classes.Doctor_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.R;

public class DoctorStudentDegree extends AppCompatActivity {

    Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_student_degree);

        buttonBackAction();
    }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_doctor_student_degree_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(DoctorStudentDegree.this, DoctorAssessStudentActivity.class)));
    }

}