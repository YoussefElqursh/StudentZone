package com.studentzone.Doctor_Classes.Doctor_Activities;

import static com.studentzone.R.drawable.custom_profile_dialoge;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputLayout;
import com.studentzone.Data_Base.Doctors;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;

public class DoctorProfileActivity extends AppCompatActivity {

    // Database object for accessing student data
    private final My_DB db = new My_DB(this);

    // Views
    private ImageView profileImage;
    private TextView tv_edite_photo;
    private TextView tv_name;
    private TextView tv_email;
    private EditText et_phone_number, et_password, phone_number_dialog_et, password_dialog_et_old_password, password_dialog_et_new_password, password_dialog_et_confirm_password;
    private TextInputLayout layout_phone_number, layout_old_password, layout_new_password, layout_confirm_password;
    private Dialog dialog_edit_phone_number, dialog_edit_password;
    private Button btn_edit_phone_number, btn_edit_password, btn_back, btn_settings, phone_number_dialog_btn_save, phone_number_dialog_btn_cancel, password_dialog_btn_save, password_dialog_btn_cancel,password_dialog_btn_done;
    private SharedPreferences preferences;
    private LinearLayout ll_new_password;
    private String oldPhoneNumber, oldPassword, wrongOldPassword, newPass, wrongNewPass , wrongConfirmPass, confirmPass, notValidPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        // Initialize views
        initializeViews();

        // Set click listener for "Edit Photo" text
        setEditPhotoTextViewAction();

        // Set click listener for back button
        setBackButtonAction();

        // Set click listener for settings button
        setSettingsButtonAction();

        // Populate user profile data
        fillOutProfileWithUserData();


        setEditPhoneNumberButtonAction();
        setEditPasswordButtonAction();

        // Set click listener for "Cancel" button in the edit phone number dialog
        setCancelEditedPhoneNumberButtonAction();


        // Set click listener for "Cancel" button in the edit password dialog
        setCancelEditedPasswordButtonAction();
    }

    /**
     * Initialize views
     **********************************************************************************************/
    public void initializeViews() {
        profileImage = findViewById(R.id.activity_doctor_profile_shiv_doctor_photo);

        tv_edite_photo = findViewById(R.id.activity_doctor_profile_tv_edit_photo);
        tv_name = findViewById(R.id.activity_doctor_profile_tv_doctor_name);
        tv_email = findViewById(R.id.activity_doctor_profile_tv_doctor_email);

        et_phone_number = findViewById(R.id.activity_doctor_profile_et_doctor_phone_number);
        et_password = findViewById(R.id.activity_doctor_profile_et_doctor_password);

        btn_back = findViewById(R.id.activity_doctor_profile_btn_back);
        btn_edit_phone_number = findViewById(R.id.activity_doctor_profile_btn_edit_phone_number);
        btn_edit_password = findViewById(R.id.activity_doctor_profile_btn_edit_password);
        btn_settings = findViewById(R.id.activity_doctor_profile_btn_sittings);


        initializeDialogEditPhoneNumber();
        initializeDialogEditPassword();
    }

    /** setEditPhotoTextViewAction()
     * set Edit Photo TextView Action
     **********************************************************************************************/
    public void setEditPhotoTextViewAction() {

        tv_edite_photo.setOnClickListener(v -> ImagePicker.with(DoctorProfileActivity.this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start());
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
        preferences.edit().putString("image_uri",String.valueOf(imageUri)).apply();
        String email = preferences.getString("email", "");

        Doctors doctor = new Doctors(null,email,null,null,null, String.valueOf(imageUri));
        db.updateDoctorData(doctor);

        // Set the result to send edited image to DoctorHomeActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("image_uri", String.valueOf(imageUri));
        setResult(RESULT_OK, resultIntent);

    }

    /** fillOutProfileWithUserData()
     *  fillOut Doctor profile with data (name,email,phone)
     **********************************************************************************************/
    public void fillOutProfileWithUserData(){

        // Retrieve user data from SharedPreferences
        // Populate views with user data
        preferences = getSharedPreferences("userInfo",MODE_PRIVATE);
        String name = preferences.getString("fName", "");
        String email = preferences.getString("email", "");
        String phoneNumber = preferences.getString("phoneNumber", "");
        String image_uri = preferences.getString("image_uri", "");
        String password = preferences.getString("password", "");


        tv_name.setText(name);
        tv_email.setText(email);
        et_password.setText(password);
        et_phone_number.setText(phoneNumber);
        profileImage.setImageURI(Uri.parse(image_uri));


    }

    /** setBackButtonAction()
     *  Back To The Previous Activity
     **********************************************************************************************/
    public void setBackButtonAction(){

        btn_back.setOnClickListener(v -> onBackPressed());
    }

    /** setSettingsButtonAction()
     *  Go To Settings Activity
     **********************************************************************************************/
    public void setSettingsButtonAction() {

        btn_settings.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), DoctorSettingsActivity.class)));
    }

    /** initializeDialogEditPhoneNumber()
     *  Initialize Dialog Edit PhoneNumber
     **********************************************************************************************/
    public void initializeDialogEditPhoneNumber() {
        dialog_edit_phone_number = new Dialog(DoctorProfileActivity.this);
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
        dialog_edit_password = new Dialog(DoctorProfileActivity.this);
        dialog_edit_password.setContentView(R.layout.fragment_edit_password_dialoge);
        dialog_edit_password.getWindow().setBackgroundDrawable(getDrawable(custom_profile_dialoge));
        dialog_edit_password.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;


        password_dialog_btn_save = dialog_edit_password.findViewById(R.id.fragment_edit_password_dialog_btn_save);
        password_dialog_btn_cancel = dialog_edit_password.findViewById(R.id.fragment_edit_password_dialog_btn_cansel);
        password_dialog_btn_done = dialog_edit_password.findViewById(R.id.fragment_edit_password_dialog_btn_done);

        password_dialog_et_old_password = dialog_edit_password.findViewById(R.id.fragment_edit_password_dialog_et_old_password);
        password_dialog_et_new_password = dialog_edit_password.findViewById(R.id.fragment_edit_password_dialog_et_new_password);
        password_dialog_et_confirm_password = dialog_edit_password.findViewById(R.id.fragment_edit_password_dialog_et_confirm_password);

        layout_old_password = dialog_edit_password.findViewById(R.id.fragment_edit_password_dialog_til_old_password);
        layout_new_password = dialog_edit_password.findViewById(R.id.fragment_edit_password_dialog_til_new_password);
        layout_confirm_password = dialog_edit_password.findViewById(R.id.fragment_edit_password_dialog_til_confirm_password);

        ll_new_password = dialog_edit_password.findViewById(R.id.fragment_edit_password_dialog_ll_new_password);
    }


    /** setEditPhoneNumberButtonAction()
     *  Set Edit Phone Number Button Action
     **********************************************************************************************/
    public void setEditPhoneNumberButtonAction() {
        btn_edit_phone_number.setOnClickListener(v -> {

            dialog_edit_phone_number.show();
            phone_number_dialog_et.setText(preferences.getString("phoneNumber", ""));

            setSaveEditedPhoneNumberButtonAction();

        });
    }


    /** setEditPasswordButtonAction()
     *  Set Edit Password Button Action
     **********************************************************************************************/
    public void setEditPasswordButtonAction() {
        btn_edit_password.setOnClickListener(v -> {

            dialog_edit_password.show();
            setDonePasswordButtonAction();
        });

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

    /** setSaveEditedPhoneNumberButtonAction()
     *  Set Save Edited PhoneNumber Button Action
     **********************************************************************************************/
    public void setSaveEditedPhoneNumberButtonAction () {

        oldPhoneNumber = preferences.getString("phoneNumber", "");

        int blueColor = getResources().getColor(R.color.blue); // Replace R.color.blue with the desired blue color resource
        layout_phone_number.setHelperTextColor(ColorStateList.valueOf(blueColor));
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
                boolean isChanged = !(s.toString().equals(oldPhoneNumber));

                phone_number_dialog_btn_save.setEnabled(isChanged);

                if (s.toString().isEmpty())
                    layout_phone_number.setHelperText("Example: 01XXXXXXXXX");
                else
                    layout_phone_number.setHelperText("");

                if(!s.toString().equals(notValidPhoneNumber))
                    layout_phone_number.setError("");

            }
        });

        phone_number_dialog_btn_save.setOnClickListener(v -> {

            // Validate the phone number format
            String phoneNumber = phone_number_dialog_et.getText().toString();

            boolean isValidPhoneNumber = phoneNumber.matches("01[0125]\\d{8}");

            // Handle error and helper messages
            if (!isValidPhoneNumber){
                layout_phone_number.setError("Enter a valid phone number");
                notValidPhoneNumber = phone_number_dialog_et.getText().toString();
            }
            else {
                String newPhoneNumber = phone_number_dialog_et.getText().toString();
                String email = tv_email.getText().toString();

                Doctors doctor = new Doctors(null,email,null,null,newPhoneNumber,null);
                db.updateDoctorData(doctor); //update in data base

                preferences.edit().putString("phoneNumber",newPhoneNumber).apply(); //update in shared Preferences userInfo file
                oldPhoneNumber = newPhoneNumber;
                et_phone_number.setText(newPhoneNumber); //update in editText profile

                dialog_edit_phone_number.cancel(); // close dialog
                ll_new_password.setVisibility(View.GONE); // hide new password layout

            }


        });
    }
    /** setCancelEditedPasswordButtonAction()
     *  Set Cancel Edited Password  Button Action
     **********************************************************************************************/
    public void setCancelEditedPasswordButtonAction () {
        password_dialog_btn_cancel.setOnClickListener(v -> {
            resetAllFields();

            // hide new password layout
            ll_new_password.setVisibility(View.GONE);
        });
    }



    /** setDoneEditedPasswordButtonAction()
     *  This method to check if Old Password Is Correct or Not
     **********************************************************************************************/
    public void setDonePasswordButtonAction() {

        if(password_dialog_et_old_password.getText().toString().isEmpty()){
            layout_old_password.setHelperText("Enter Old Password.");
            password_dialog_et_old_password.requestFocus();

            int blueColor = getResources().getColor(R.color.blue); // Replace R.color.blue with the desired blue color resource
            layout_old_password.setHelperTextColor(ColorStateList.valueOf(blueColor));

        }

        password_dialog_et_old_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().isEmpty()) {
                    layout_old_password.setHelperText("Enter old password.");
                    int blueColor = getResources().getColor(R.color.blue); // Replace R.color.blue with the desired blue color resource
                    layout_old_password.setHelperTextColor(ColorStateList.valueOf(blueColor));
                    layout_old_password.setPasswordVisibilityToggleTintList(getColorStateList(R.color.blue));
                }
                else  if(!s.equals(wrongOldPassword)){
                    layout_old_password.setHelperText("");//This to delete error message if user tray to writ again after he writ old password wrong
                    layout_old_password.setError(null);

                }
                else{
                    layout_old_password.setHelperText("");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                oldPassword = password_dialog_et_old_password.getText().toString();

            }
        });

        password_dialog_btn_done.setOnClickListener(v -> {

            String correctPassword =  preferences.getString("password", "");

            if(password_dialog_et_old_password.getText().toString().equals(correctPassword)){
                layout_old_password.setHelperText("Correct Password.");
                password_dialog_et_old_password.setEnabled(false);
                password_dialog_et_new_password.setEnabled(true);
                password_dialog_et_confirm_password.setEnabled(true);
                password_dialog_btn_done.setEnabled(false);
                password_dialog_btn_save.setEnabled(true);

                layout_new_password.setEnabled(true);
                layout_confirm_password.setEnabled(true);

                password_dialog_et_new_password.requestFocus();

                // Replace R.color.blue with the desired blue color resource
                int grayColor = getResources().getColor(R.color.green);

                layout_old_password.setHelperTextColor(ColorStateList.valueOf(grayColor));

                setSaveNewPasswordButtonAction();

                // show new password layout
                ll_new_password.setVisibility(View.VISIBLE);

            }

            else {
                layout_old_password.setHelperText("Wrong Password!");
                wrongOldPassword = password_dialog_et_old_password.getText().toString();

            }

        });
    }

    /** setSaveNewPasswordButtonAction()
     *  Set Save Edited Password Button Action
     **********************************************************************************************/
    public void setSaveNewPasswordButtonAction() {

        if(password_dialog_et_new_password.getText().toString().isEmpty()){
            layout_new_password.setHelperText("Enter New Password.");
            int blueColor = getResources().getColor(R.color.blue); // Replace R.color.blue with the desired blue color resource
            layout_new_password.setHelperTextColor(ColorStateList.valueOf(blueColor));
        }

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                String newPassword = password_dialog_et_new_password.getText().toString();

                if(newPassword.isEmpty()){
                    layout_new_password.setHelperText("Enter New Password.");

                }
                else if (newPassword.length() < 6){
                    layout_new_password.setHelperText("Password Too Short.");
                }
                else{
                    layout_new_password.setHelperText("");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                newPass = password_dialog_et_new_password.getText().toString();
                confirmPass = password_dialog_et_confirm_password.getText().toString();


                if (!newPass.equals(wrongNewPass)) {
                    //This to delete error message if user tray to writ again after he wrote new password wrong
                    layout_new_password.setError("");
                    layout_confirm_password.setError("");
                }
                if (!confirmPass.equals(wrongConfirmPass)) {
                    //This to delete error message if user tray to writ again after he wrote new password wrong
                    layout_confirm_password.setError("");
                }
            }
        };
        password_dialog_et_new_password.addTextChangedListener(textWatcher);
        password_dialog_et_confirm_password.addTextChangedListener(textWatcher);

        password_dialog_btn_save.setOnClickListener(v -> {

            String newPassword = password_dialog_et_new_password.getText().toString();
            String confirmPassword = password_dialog_et_confirm_password.getText().toString();

            wrongNewPass = newPassword;
            wrongConfirmPass = confirmPassword;


            // Validate the password format
            boolean isNewMatchOld = newPassword.equals(oldPassword);
            boolean isPasswordLengthLessThanSix = newPassword.length() < 6;
            boolean isValidPassword = newPassword. matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*]).*$");
            boolean isMatchingPasswords = newPassword.equals(confirmPassword);

            if (isPasswordLengthLessThanSix) {
                layout_new_password.setHelperText("Password must be at least 6 characters long.");
            }
            else if (isNewMatchOld) {
                layout_new_password.setHelperText("Please choose a different password. New password cannot be the same as the old password.");
            }
            else if (!isValidPassword) {
                layout_new_password.setHelperText("Password must contain at least one number, one letter, and one special character (!@#$%^&*)");
            }

            if(!isMatchingPasswords){
                layout_confirm_password.setHelperText("Passwords don't Match!");
            }

            if(!isNewMatchOld && !isPasswordLengthLessThanSix && isValidPassword && isMatchingPasswords)
            {
                String email = tv_email.getText().toString();

                Doctors doctor = new Doctors(null,email,newPassword,null,null,null);
                db.updateDoctorData(doctor);//update in data base

                preferences.edit().putString("password",newPassword).apply(); //update in shared Preferences userInfo file


                resetAllFields();
                et_password.setText(newPassword);

            }

        });
    }

    /** resetAllFields()
     **********************************************************************************************/
    public void resetAllFields() {

        dialog_edit_password.dismiss();
        password_dialog_et_old_password.getText().clear();
        password_dialog_et_new_password.getText().clear();
        password_dialog_et_confirm_password.getText().clear();

        password_dialog_et_old_password.setEnabled(true);
        password_dialog_et_new_password.setEnabled(false);
        password_dialog_et_confirm_password.setEnabled(false);

        layout_old_password.setError(null);
        layout_new_password.setError(null);
        layout_confirm_password.setError(null);

        layout_old_password.setHelperText(null);
        layout_new_password.setHelperText(null);
        layout_confirm_password.setHelperText(null);

        password_dialog_btn_save.setEnabled(false);
        password_dialog_btn_done.setEnabled(true);


    }
}