package com.studentzone.Student_Classes.Student_Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.studentzone.Admin_Classes.Admin_Activities.AdminProfileActivity;
import com.studentzone.R;

public class StudentSettingsActivity extends AppCompatActivity {

    private Button btn_back;
    private ImageView userImage;
    private static final int PROFILE_IMAGE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_settings);

        setBackButtonAction();
        setUserNameAndImage();
        openEditePersonalInfo();
    }

    /** setBackButtonAction()
     *  Back To The Previous Activity
     **********************************************************************************************/
    public void setBackButtonAction(){
        btn_back = findViewById(R.id.activity_student_settings_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), StudentHomeActivity.class)));
    }

    /** setUserNameAndImage()
     **********************************************************************************************/
    public void setUserNameAndImage(){
        SharedPreferences preferences = getSharedPreferences("userInfo",MODE_PRIVATE);
        String name = preferences.getString("fName", "");
        String image_uri = preferences.getString("image_uri", "");
        String gender = preferences.getString("gender", "");



        TextView tv_userName = findViewById(R.id.profileName_tv);
        userImage = findViewById(R.id.profile_IV);

        tv_userName.setText(name);
        if(!image_uri.isEmpty())
          userImage.setImageURI(Uri.parse(image_uri));
        else {
            // Set the default image if no image_uri is available
            if(gender.equals("Female"))
                userImage.setImageResource(R.drawable.ic_female_student);
            else
                userImage.setImageResource(R.drawable.ic_male_student);
        }

    }

    /** openEditePersonalInfo()
     *  Opens the Edit Personal Info screen when the profile card is clicked.
     **********************************************************************************************/
    public void openEditePersonalInfo()
    {
        CardView cv_profile = findViewById(R.id.activity_student_settings_cv_profile);
        cv_profile.setOnClickListener(v -> {
            Intent intent = new Intent(StudentSettingsActivity.this, StudentProfileActivity.class);

            intent.putExtra("request_code", PROFILE_IMAGE_REQUEST_CODE);
            startActivityForResult(intent, PROFILE_IMAGE_REQUEST_CODE);
        });
    }

    /* this methode receive edited image from AdminProfileActivity ,
     * to reflect the changes in StudentSettingsActivity at the same time
     **********************************************************************************************/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PROFILE_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {

            assert data != null;
            String updatedImageUri = data.getStringExtra("image_uri");

            // Update the image in StudentSettingsActivity using the received imageUri
            // Check if the image_uri is empty or null
            if (updatedImageUri != null && !updatedImageUri.isEmpty()) {
                userImage.setImageURI(Uri.parse(updatedImageUri));
            }
        }
    }
}