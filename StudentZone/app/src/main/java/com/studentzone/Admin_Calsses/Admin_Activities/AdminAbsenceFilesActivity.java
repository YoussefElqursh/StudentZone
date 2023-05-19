package com.studentzone.Admin_Calsses.Admin_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.R;

public class AdminAbsenceFilesActivity extends AppCompatActivity {
    Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_absence_files);
        buttonBackAction();
        }
    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_admin_absence_files_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(AdminAbsenceFilesActivity.this,AdminHomeActivity.class)));
    }
}