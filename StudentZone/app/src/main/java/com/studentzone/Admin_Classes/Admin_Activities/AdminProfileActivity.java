package com.studentzone.Admin_Classes.Admin_Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;

public class AdminProfileActivity extends AppCompatActivity {

    ImageView profileImage;
    TextView tv_edite_photo,tv_name, tv_email;

    EditText et_phone_number, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        inflate();
        setEditPhotoTextViewAction();
        fillOutProfileWithUserData();
        setBackButtonAction();
    }

    /**
     * Inflate
     **********************************************************************************************/
    public void inflate() {
        profileImage = findViewById(R.id.activity_admin_profile_shiv_admin_photo);
        tv_edite_photo = findViewById(R.id.activity_admin_profile_tv_edit_photo);
        tv_name = findViewById(R.id.activity_admin_profile_tv_admin_name);
        tv_email = findViewById(R.id.activity_admin_profile_tv_admin_email);
        et_phone_number = findViewById(R.id.activity_admin_profile_et_admin_phone_number);
        et_password = findViewById(R.id.activity_admin_profile_et_admin_password);
    }

    /** setEditPhotoTextViewAction()
     * set Edit Photo TextView Action
     **********************************************************************************************/
    public void setEditPhotoTextViewAction() {

        tv_edite_photo.setOnClickListener(v -> ImagePicker.with(AdminProfileActivity.this)
                .crop()	    			 //Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        assert data != null;
        Uri uri = data.getData();

        profileImage.setImageURI(uri);


        My_DB db = new My_DB(this);
        SharedPreferences preferences = getSharedPreferences("userInfo",MODE_PRIVATE);

        preferences.edit().putString("image_uri",String.valueOf(uri)).apply();

        String email = preferences.getString("email", "");
        db.updateAdminImage(email, String.valueOf(uri));
    }

    /** fillOutProfileWithUserData()
     *  fillOut Admin Data (name,email,phone)
     **********************************************************************************************/
    public void fillOutProfileWithUserData(){
        SharedPreferences preferences = getSharedPreferences("userInfo",MODE_PRIVATE);

        String name = preferences.getString("fName", "");
        String email = preferences.getString("email", "");
        String phone = preferences.getString("phoneNumber", "");
        String image_uri = preferences.getString("image_uri", "");

        tv_name.setText(name);
        tv_email.setText(email);
        et_phone_number.setText(phone);
        profileImage.setImageURI(Uri.parse(image_uri));
    }

    /** setBackButtonAction()
     *  Back To The Previous Activity
     **********************************************************************************************/
    public void setBackButtonAction() {
        Button btn_back = findViewById(R.id.activity_admin_profile_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}