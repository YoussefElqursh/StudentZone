package com.studentzone.Student_Classes.Student_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.R;

public class StudentSubjectPdfsActivity extends AppCompatActivity {

    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_subject_pdfs);
        buttonBackAction();
    }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_student_subject_Pdfs_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(StudentSubjectPdfsActivity.this, StudentHomeActivity.class)));
    }
}