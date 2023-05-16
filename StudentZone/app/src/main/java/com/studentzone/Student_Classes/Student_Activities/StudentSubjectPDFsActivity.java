package com.studentzone.Student_Classes.Student_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.Admin_Calsses.Admin_Activities.AdminDepartmentsActivity;
import com.studentzone.Admin_Calsses.Admin_Activities.AdminHomeActivity;
import com.studentzone.R;

public class StudentSubjectPDFsActivity extends AppCompatActivity {

    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_subject_pdfs);
        buttonBack = findViewById(R.id.activity_student_subject_PDFs_btn_back);
        buttonBack.setOnClickListener(v -> startActivity(new Intent(StudentSubjectPDFsActivity.this, StudentHomeActivity.class)));
    }
}