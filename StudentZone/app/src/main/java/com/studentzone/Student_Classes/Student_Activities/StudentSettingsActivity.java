package com.studentzone.Student_Classes.Student_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.Admin_Classes.Admin_Activities.AdminHomeActivity;
import com.studentzone.R;

public class StudentSettingsActivity extends AppCompatActivity {

    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_settings);

        setBackButtonAction();
    }

    /** setBackButtonAction()
     *  Back To The Previous Activity
     **********************************************************************************************/
    public void setBackButtonAction(){
        btn_back = findViewById(R.id.activity_student_settings_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), StudentHomeActivity.class)));
    }
}