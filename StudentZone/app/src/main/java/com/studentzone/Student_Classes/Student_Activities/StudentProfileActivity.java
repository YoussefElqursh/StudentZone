package com.studentzone.Student_Classes.Student_Activities;

import static com.studentzone.R.drawable.custom_profile_dialoge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputLayout;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;

public class StudentProfileActivity extends AppCompatActivity {

    // Database object for accessing student data
    private final My_DB db = new My_DB(this);

    // Views
    private ImageView profileImage;
    private TextView tv_edite_photo,tv_name, tv_email, tv_aid, tv_dept;
    private EditText et_phone_number, et_password, phone_number_dialog_et;
    private TextInputLayout layout_phone_number;
    private Dialog dialog_edit_phone_number, dialog_edit_password;
    private Button btn_edit_phone_number, btn_edit_password, btn_back, phone_number_dialog_btn_save, phone_number_dialog_btn_cancel;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);


        // Initialize views
        initializeViews();

        // Set click listener for "Edit Photo" text
        setEditPhotoTextViewAction();

        // Set click listener for back button
        setBackButtonAction();

        // Populate user profile data
        fillOutProfileWithUserData();

        initializeDialogEditPhoneNumber();
        initializeDialogEditPassword();

        setEditPhoneNumberButtonAction();
        setEditPasswordButtonAction();

        // Set click listener for "Cancel" button in the edit phone number dialog
        setCancelEditedPhoneNumberButtonAction();

        // Set click listener for "Save" button in the edit phone number dialog
        setSaveEditedPhoneNumberButtonAction();
    }

    /**
     * Initialize views
     **********************************************************************************************/
    public void initializeViews() {
        profileImage = findViewById(R.id.activity_student_profile_shiv_student_photo);

        tv_edite_photo = findViewById(R.id.activity_student_profile_tv_edit_photo);
        tv_name = findViewById(R.id.activity_student_profile_tv_student_name);
        tv_email = findViewById(R.id.activity_student_profile_tv_student_email);

        et_phone_number = findViewById(R.id.activity_student_profile_et_student_phone_number);
        et_password = findViewById(R.id.activity_student_profile_et_student_password);

        tv_aid = findViewById(R.id.activity_student_profile_tv_student_academic_id);
        tv_dept = findViewById(R.id.activity_student_profile_tv_student_department);

        btn_edit_phone_number = findViewById(R.id.activity_student_profile_btn_edit_phone_number);
        btn_edit_password = findViewById(R.id.activity_student_profile_btn_edit_password);
        btn_back = findViewById(R.id.activity_student_profile_btn_back);
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

        assert data != null;
        // Retrieve the selected image URI
        Uri imageUri = data.getData();

        // Update profile image view
        profileImage.setImageURI(imageUri);

        // Save image URI in SharedPreferences and database
        preferences = getSharedPreferences("userInfo",MODE_PRIVATE);
        preferences.edit().putString("image_uri",String.valueOf(imageUri)).apply();

        String email = preferences.getString("email", "");
        db.updateStudentImage(email, String.valueOf(imageUri));


        // Set the result to send edited image to StudentHomeActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("image_uri", String.valueOf(imageUri));
        setResult(RESULT_OK, resultIntent);
    }

    /** fillOutProfileWithUserData()
     *   Fill out student profile with user data (name,email,phone,aid,department,)
     **********************************************************************************************/
    public void fillOutProfileWithUserData(){

        // Retrieve user data from SharedPreferences
        // Populate views with user data

        preferences = getSharedPreferences("userInfo",MODE_PRIVATE);

        String name = preferences.getString("fName", "");
        String email = preferences.getString("email", "");
        String password = preferences.getString("password", "");
        String aid = preferences.getString("aid", "");
        String dept = preferences.getString("department", "");
        String phoneNumber = preferences.getString("phoneNumber", "");
        String image_uri = preferences.getString("image_uri", "");


        tv_name.setText(name);
        tv_email.setText(email);
        et_password.setText(password);
        tv_aid.setText(aid);
        tv_dept.setText(dept);
        et_phone_number.setText(phoneNumber);
        profileImage.setImageURI(Uri.parse(image_uri));


    }

    /** setBackButtonAction()
     *  Back To The Previous Activity
     **********************************************************************************************/
    public void setBackButtonAction(){
        btn_back.setOnClickListener(v -> onBackPressed());

    }
    /** initializeDialogEditPhoneNumber()
     *  Initialize Dialog Edit PhoneNumber
     **********************************************************************************************/
    public void initializeDialogEditPhoneNumber() {
        dialog_edit_phone_number = new Dialog(StudentProfileActivity.this);
        dialog_edit_phone_number.setContentView(R.layout.fragment_edit_phone_number_dialoge);
        dialog_edit_phone_number.getWindow().setBackgroundDrawable(getDrawable(custom_profile_dialoge));
        dialog_edit_phone_number.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;

        phone_number_dialog_et = dialog_edit_phone_number.findViewById(R.id.fragment_edit_phone_number_dialog_et_phone_number);
        layout_phone_number = dialog_edit_phone_number.findViewById(R.id.fragment_edit_phone_number_dialog_til_phone_number);

        phone_number_dialog_btn_save = dialog_edit_phone_number.findViewById(R.id.fragment_edit_phone_number_dialog_btn_save);
        phone_number_dialog_btn_cancel = dialog_edit_phone_number.findViewById(R.id.fragment_edit_phone_number_dialog_btn_cansel);
    }

    /** initializeDialogEditPassword()
     *  Initialize Dialog Edit Password
     **********************************************************************************************/
    public void initializeDialogEditPassword() {
        dialog_edit_password = new Dialog(StudentProfileActivity.this);
        dialog_edit_password.setContentView(R.layout.fragment_edit_password_dialoge);
        dialog_edit_password.getWindow().setBackgroundDrawable(getDrawable(custom_profile_dialoge));
        dialog_edit_password.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
    }


    /** setEditPhoneNumberButtonAction()
     *  Set Edit Phone Number Button Action
     **********************************************************************************************/
    public void setEditPhoneNumberButtonAction() {
        btn_edit_phone_number.setOnClickListener(v -> {
            dialog_edit_phone_number.show();
            phone_number_dialog_et.setText(preferences.getString("phoneNumber", ""));
            isPhoneNumberChanged();
        });

    }

    /** setEditPasswordButtonAction()
     *  Set Edit Password Button Action
     **********************************************************************************************/
    public void setEditPasswordButtonAction() {
        btn_edit_password.setOnClickListener(v -> dialog_edit_password.show());
    }

    /** setCancelEditedPhoneNumberButtonAction()
     *  Set Cancel Edited PhoneNumber  Button Action
     **********************************************************************************************/
    public void setCancelEditedPhoneNumberButtonAction () {
        phone_number_dialog_btn_cancel.setOnClickListener(v -> {

            dialog_edit_phone_number.cancel();
            phone_number_dialog_et.getText().clear();
        });
    }

    /** isPhoneNumberChanged()
     *  this method to monitor is phone number changed set button save enabled else set it disabled
     **********************************************************************************************/
    private void isPhoneNumberChanged(){

        String originalPhoneNumber = preferences.getString("phoneNumber", "");

        phone_number_dialog_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                // Check if the phone number has changed
                boolean isChanged = !(phone_number_dialog_et.getText().toString().equals(originalPhoneNumber));

                // Validate the phone number format
                String phoneNumber = phone_number_dialog_et.getText().toString();

                boolean isValidPhoneNumber = phoneNumber.matches("01[0125]\\d{8}");


                // Handle error and helper messages
                if (phoneNumber.length() == 0) {
                    layout_phone_number.setError("");
                    layout_phone_number.setHelperText("Example: 01XXXXXXXXX");
                    phone_number_dialog_btn_save.setEnabled(false);
                }
                else if (!isValidPhoneNumber) {
                    layout_phone_number.setError("Enter a valid Egyptian phone number");
                    layout_phone_number.setHelperText("");
                    phone_number_dialog_btn_save.setEnabled(false);
                }
                else {
                    layout_phone_number.setError("");
                    phone_number_dialog_btn_save.setEnabled(true);
                }

                // Enable/disable save button based on phone number validity
                phone_number_dialog_btn_save.setEnabled(isChanged && isValidPhoneNumber);
            }
        });
    }

    /** setSaveEditedPhoneNumberButtonAction()
     *  Set Save Edited PhoneNumber Button Action
     **********************************************************************************************/
    public void setSaveEditedPhoneNumberButtonAction () {
        phone_number_dialog_btn_save.setOnClickListener(v -> {

            String newPhoneNumber = phone_number_dialog_et.getText().toString();


            db.updateStudentPhoneNumber(tv_email.getText().toString(),newPhoneNumber); //update in data base

            preferences.edit().putString("phoneNumber",newPhoneNumber).apply(); //update in shared Preferences userInfo file
            et_phone_number.setText(newPhoneNumber); //update in editText profile

            dialog_edit_phone_number.cancel(); // close dialog

        });
    }
}