package com.studentzone.Welcome_Classes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.studentzone.Data_Base.My_DB;


import com.studentzone.Login_Classes.Login_Activities.LoginActivity;
import com.studentzone.R;

public class WelcomeActivity extends AppCompatActivity {
    Button btn_welcome;
    My_DB  helper;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        checkFirstOpen();

        btn_welcome = findViewById(R.id.activity_welcome_btn_welcome);
        buttonWelcomeAction();


    }


    /** Welcome Button
     ******************************************************************************************/
    public void buttonWelcomeAction(){

        imageView = findViewById(R.id.welcome_iv);
        btn_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),LoginActivity.class);
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

