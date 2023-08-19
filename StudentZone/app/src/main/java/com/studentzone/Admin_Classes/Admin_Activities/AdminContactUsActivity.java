package com.studentzone.Admin_Classes.Admin_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.studentzone.R;

public class AdminContactUsActivity extends AppCompatActivity {

    ImageView map , facebook , website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_contact_us);

        openMap();
        openFacebook();
        openWebsite();
    }

    public void openMap()
    {
        map = findViewById(R.id.activity_admin_contact_us_maps);

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

    public void gotoUrl(String s)
    {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}