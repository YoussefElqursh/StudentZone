package com.studentzone.Admin_Classes.Admin_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import com.studentzone.R;

public class AdminSettingsActivity extends AppCompatActivity {

    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_settings);

        setBackButtonAction();
    }

    /** setBackButtonAction()
     *  Back To The Previous Activity
     **********************************************************************************************/
    public void setBackButtonAction(){
        btn_back = findViewById(R.id.activity_admin_settings_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(getBaseContext(),AdminHomeActivity.class)));
    }
}