package com.studentzone.Student_Classes.Student_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.studentzone.Admin_Classes.Admin_Activities.AdminHomeActivity;
import com.studentzone.R;

public class StudentContactUsActivity extends AppCompatActivity {

    ImageView map , facebook , website;

    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_contact_us);

        openMap();
        openFacebook();
        openWebsite();
        setBackButtonAction();
    }

    public void gotoUrl(String s)
    {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    public void openMap()
    {
        map = findViewById(R.id.activity_student_contact_us_maps);

        map.setOnClickListener(v -> {
            Uri uri = Uri.parse("geo:0, 0?q=Menoufia University");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
        });
    }



    public void openFacebook()
    {
        facebook = findViewById(R.id.facebook_iv);

        facebook.setOnClickListener(v -> {
            gotoUrl("https://www.facebook.com/MenofiaUniv");
        });
    }

    public void openWebsite()
    {
        website = findViewById(R.id.website_iv);

        website.setOnClickListener(v -> {
            gotoUrl("http://mu.menofia.edu.eg");
        });
    }

    /** setBackButtonAction()
     *  Back To The Previous Activity
     **********************************************************************************************/
    public void setBackButtonAction(){
        btn_back = findViewById(R.id.activity_student_contact_us_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), StudentHomeActivity.class)));
    }

}