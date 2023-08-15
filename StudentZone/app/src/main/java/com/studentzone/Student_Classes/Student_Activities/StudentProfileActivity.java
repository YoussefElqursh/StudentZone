package com.studentzone.Student_Classes.Student_Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;

public class StudentProfileActivity extends AppCompatActivity {

    ImageView profileImage;
    TextView tv_edite_photo,tv_name, tv_email, tv_phone_number, tv_aid, tv_dept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        inflate();
        setEditPhotoTextViewAction();
        fillOutProfileWithUserData();
        setBackButtonAction();
    }

    /**
     * Inflate
     **********************************************************************************************/
    public void inflate() {
        profileImage = findViewById(R.id.activity_student_profile_shiv_student_photo);
        tv_edite_photo = findViewById(R.id.activity_student_profile_tv_edit_photo);
        tv_name = findViewById(R.id.activity_student_profile_tv_student_name);
        tv_email = findViewById(R.id.activity_student_profile_tv_student_email);
        tv_phone_number = findViewById(R.id.activity_student_profile_tv_student_phone_number);
        tv_aid = findViewById(R.id.activity_student_profile_tv_student_academic_id);
        tv_dept = findViewById(R.id.activity_student_profile_tv_student_department);
    }

    /** setEditPhotoTextViewAction()
     * set Edit Photo TextView Action
     **********************************************************************************************/
    public void setEditPhotoTextViewAction() {

        tv_edite_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(StudentProfileActivity.this)
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
        Uri uri = data.getData();
        profileImage.setImageURI(uri);


        My_DB db = new My_DB(this);
        SharedPreferences preferences = getSharedPreferences("userInfo",MODE_PRIVATE);

        preferences.edit().putString("image_uri",String.valueOf(uri)).apply();

        String email = preferences.getString("email", "");
        db.updateStudentImage(email, String.valueOf(uri));
    }

    /** fillOutProfileWithUserData()
     *  fillOut Student Data (name,email,phone,aid,department,)
     **********************************************************************************************/
    public void fillOutProfileWithUserData(){

        SharedPreferences preferences = getSharedPreferences("userInfo",MODE_PRIVATE);
        String name = preferences.getString("fName", "");
        String email = preferences.getString("email", "");
        String aid = preferences.getString("aid", "");
        String dept = preferences.getString("department", "");
        String phoneNumber = preferences.getString("phoneNumber", "");
        String image_uri = preferences.getString("image_uri", "");


        tv_name.setText(name);
        tv_email.setText(email);
        tv_aid.setText(aid);
        tv_dept.setText(dept);
        tv_phone_number.setText(phoneNumber);
        profileImage.setImageURI(Uri.parse(image_uri));


    }

    /** setBackButtonAction()
     *  Back To The Previous Activity
     **********************************************************************************************/
    public void setBackButtonAction(){
        Button btn_back = findViewById(R.id.activity_student_profile_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
    }
}