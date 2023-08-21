package com.studentzone.Doctor_Classes.Doctor_Activities;

import static com.studentzone.R.drawable.custom_profile_dialoge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.studentzone.Admin_Classes.Admin_Activities.AdminProfileActivity;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;

public class DoctorProfileActivity extends AppCompatActivity {

    ImageView profileImage;
    TextView tv_edite_photo,tv_name, tv_email;
    EditText et_phone_number, et_password;
    Dialog dialog_edit_phone_number, dialog_edit_password;
    Button btn_edit_phone_number, btn_edit_password, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        inflate();
        setEditPhotoTextViewAction();
        fillOutProfileWithUserData();
        setBackButtonAction();

        initializeDialogEditPhoneNumber();
        initializeDialogEditPassword();

        setEditPhoneNumberButtonAction();
        setEditPasswordButtonAction();

    }

    /**
     * Inflate
     **********************************************************************************************/
    public void inflate() {
        profileImage = findViewById(R.id.activity_doctor_profile_shiv_doctor_photo);

        tv_edite_photo = findViewById(R.id.activity_doctor_profile_tv_edit_photo);
        tv_name = findViewById(R.id.activity_doctor_profile_tv_doctor_name);
        tv_email = findViewById(R.id.activity_doctor_profile_tv_doctor_email);

        et_phone_number = findViewById(R.id.activity_doctor_profile_et_doctor_phone_number);
        et_password = findViewById(R.id.activity_doctor_profile_et_doctor_password);

        btn_back = findViewById(R.id.activity_doctor_profile_btn_back);
        btn_edit_phone_number = findViewById(R.id.activity_doctor_profile_btn_edit_phone_number);
        btn_edit_password = findViewById(R.id.activity_doctor_profile_btn_edit_password);
    }

    /** setEditPhotoTextViewAction()
     * set Edit Photo TextView Action
     **********************************************************************************************/
    public void setEditPhotoTextViewAction() {

        tv_edite_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(DoctorProfileActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });
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
        db.updateDoctorImage(email, String.valueOf(uri));

        // Set the result to send edited image to DoctorHomeActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("image_uri", String.valueOf(uri));
        setResult(RESULT_OK, resultIntent);

    }

    /** fillOutProfileWithUserData()
     *  fillOut Doctor Data (name,email,phone)
     **********************************************************************************************/
    public void fillOutProfileWithUserData(){

        SharedPreferences preferences = getSharedPreferences("userInfo",MODE_PRIVATE);
        String name = preferences.getString("fName", "");
        String email = preferences.getString("email", "");
        String phoneNumber = preferences.getString("phoneNumber", "");
        String image_uri = preferences.getString("image_uri", "");


        tv_name.setText(name);
        tv_email.setText(email);
        et_phone_number.setText(phoneNumber);
        profileImage.setImageURI(Uri.parse(image_uri));


    }

    /** setBackButtonAction()
     *  Back To The Previous Activity
     **********************************************************************************************/
    public void setBackButtonAction(){
        btn_back = findViewById(R.id.activity_doctor_profile_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
    }

    /** initializeDialogEditPhoneNumber()
     *  Initialize Dialog Edit PhoneNumber
     **********************************************************************************************/
    public void initializeDialogEditPhoneNumber() {
        dialog_edit_phone_number = new Dialog(DoctorProfileActivity.this);
        dialog_edit_phone_number.setContentView(R.layout.fragment_edit_phone_number_dialoge);
        dialog_edit_phone_number.getWindow().setBackgroundDrawable(getDrawable(custom_profile_dialoge));
        dialog_edit_phone_number.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
    }

    /** initializeDialogEditPassword()
     *  Initialize Dialog Edit Password
     **********************************************************************************************/
    public void initializeDialogEditPassword() {
        dialog_edit_password = new Dialog(DoctorProfileActivity.this);
        dialog_edit_password.setContentView(R.layout.fragment_edit_password_dialoge);
        dialog_edit_password.getWindow().setBackgroundDrawable(getDrawable(custom_profile_dialoge));
        dialog_edit_password.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
    }


    /** setEditPhoneNumberButtonAction()
     *  Set Edit Phone Number Button Action
     **********************************************************************************************/
    public void setEditPhoneNumberButtonAction() {
        btn_edit_phone_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_edit_phone_number.show();
            }
        });

    }

    /** setEditPasswordButtonAction()
     *  Set Edit Password Button Action
     **********************************************************************************************/
    public void setEditPasswordButtonAction() {
        btn_edit_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_edit_password.show();
            }
        });

    }
}