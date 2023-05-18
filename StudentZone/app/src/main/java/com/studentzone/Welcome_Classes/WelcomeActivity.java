package com.studentzone.Welcome_Classes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.studentzone.Admin_Calsses.Admin_Activities.AdminHomeActivity;
import com.studentzone.Doctor_Classes.Doctor_Activities.DoctorHomeActivity;
import com.studentzone.Doctor_Classes.Doctor_Activities.DoctorSubjectPdfsActivity;

import com.studentzone.Login_Classes.Login_Activities.LoginActivity;
import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Activities.StudentHomeActivity;

public class WelcomeActivity extends AppCompatActivity {
    Button btn_welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        checkFirstOpen();
        btn_welcome = findViewById(R.id.activity_welcome_btn_welcome);


        /** Welcome Button
         ******************************************************************************************/
        btn_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

        /** checkFirstOpen()
         *  This Methode To Open The Welcome  Only At The First Time When User Install The APP.
         *  @var isFirstRun This Param Cary Value Of PREFERENCE File Which Is (True Or False).
         *  if (!isFirstRun) >>>> To Check If it False , Open Login Activity Direct.
         *  The Last Line In Method >>>  To save The Value Of IsFirstRun Into PREFERENCE File
         ******************************************************************************************/
        private void checkFirstOpen(){
            Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .getBoolean("isFirstRun", true);

            if (!isFirstRun) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().
                    putBoolean("isFirstRun", false).apply();
        }
   }