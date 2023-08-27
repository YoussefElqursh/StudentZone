package com.studentzone.Admin_Classes.Admin_Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.studentzone.R;

public class AdminSettingsActivity extends AppCompatActivity {

    private Button btn_back;
    private ImageView userImage;
    private static final int PROFILE_IMAGE_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_settings);

        setBackButtonAction();
        setUserNameAndImage();
        openEditePersonalInfo();
    }

    /** setBackButtonAction()
     *  Back To The Previous Activity
     **********************************************************************************************/
    public void setBackButtonAction(){
        btn_back = findViewById(R.id.activity_admin_settings_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(getBaseContext(),AdminHomeActivity.class)));
    }

    /** setUserNameAndImage()
     **********************************************************************************************/
    public void setUserNameAndImage(){

        SharedPreferences preferences = getSharedPreferences("userInfo",MODE_PRIVATE);
        String name = preferences.getString("fName", "");
        String image_uri = preferences.getString("image_uri", "");


        TextView tv_userName = findViewById(R.id.profileName_tv);
        userImage = findViewById(R.id.profile_IV);

        tv_userName.setText(name);
        userImage.setImageURI(Uri.parse(image_uri));

    }

    /** openEditePersonalInfo()
     *  Opens the Edit Personal Info screen when the profile card is clicked.
     **********************************************************************************************/
    public void openEditePersonalInfo()
    {
        CardView cv_profile = findViewById(R.id.activity_admin_settings_cv_profile);
        cv_profile.setOnClickListener(v -> {
            Intent intent = new Intent(AdminSettingsActivity.this, AdminProfileActivity.class);

            intent.putExtra("request_code", PROFILE_IMAGE_REQUEST_CODE);
            startActivityForResult(intent, PROFILE_IMAGE_REQUEST_CODE);
        });

    }

    /* this methode receive edited image from AdminProfileActivity ,
     * to reflect the changes in AdminSettingsActivity at the same time
     **********************************************************************************************/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PROFILE_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {

            assert data != null;
            String updatedImageUri = data.getStringExtra("image_uri");

            // Update the image in AdminSettingsActivity using the received imageUri
            userImage.setImageURI(Uri.parse(updatedImageUri));

        }
    }
}