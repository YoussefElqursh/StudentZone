package com.studentzone.Admin_Classes.Admin_Activities;

import static com.studentzone.R.drawable.custom_profile_dialoge;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputLayout;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;

public class AdminProfileActivity extends AppCompatActivity {

    // Database object for accessing student data
    private final My_DB db = new My_DB(this);

    // Views
    private ImageView profileImage;
    private TextView tv_edite_photo,tv_name, tv_email;
    private EditText et_phone_number, et_password, phone_number_dialog_et, password_dialog_et_old_password, password_dialog_et_new_password, password_dialog_et_confirm_password;
    private TextInputLayout layout_phone_number, layout_old_password, layout_new_password, layout_confirm_password;
    private Dialog dialog_edit_phone_number, dialog_edit_password;
    private Button btn_edit_phone_number, btn_edit_password, btn_back, phone_number_dialog_btn_save, phone_number_dialog_btn_cancel, password_dialog_btn_save, password_dialog_btn_cancel;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        // Initialize views
        initializeViews();

        // Set click listener for "Edit Photo" text
        setEditPhotoTextViewAction();

        // Set click listener for back button
        setBackButtonAction();

        // Populate user profile data
        fillOutProfileWithUserData();

        setEditPhoneNumberButtonAction();
        setEditPasswordButtonAction();


        // Set click listener for "Cancel" button in the edit phone number dialog
        setCancelEditedPhoneNumberButtonAction();

        // Set click listener for "Save" button in the edit phone number dialog
        setSaveEditedPhoneNumberButtonAction();

        // Set click listener for "Cancel" button in the edit password dialog
        setCancelEditedPasswordButtonAction();

        // Set click listener for "Save" button in the edit password dialog
        setSaveEditedPasswordButtonAction();
    }

    /**
     * Initialize views
     **********************************************************************************************/
    public void initializeViews() {
        profileImage = findViewById(R.id.activity_admin_profile_shiv_admin_photo);
        profileImage.setImageResource(R.drawable.ic_male_student);

        tv_edite_photo = findViewById(R.id.activity_admin_profile_tv_edit_photo);
        tv_name = findViewById(R.id.activity_admin_profile_tv_admin_name);
        tv_email = findViewById(R.id.activity_admin_profile_tv_admin_email);

        et_phone_number = findViewById(R.id.activity_admin_profile_et_admin_phone_number);
        et_password = findViewById(R.id.activity_admin_profile_et_admin_password);

        btn_back = findViewById(R.id.activity_admin_profile_btn_back);
        btn_edit_phone_number = findViewById(R.id.activity_admin_profile_btn_edit_phone_number);
        btn_edit_password = findViewById(R.id.activity_admin_profile_btn_edit_password);


        initializeDialogEditPhoneNumber();
        initializeDialogEditPassword();
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
        // Retrieve the selected image URI
        Uri imageUri = data.getData();

        // Update profile image view
        profileImage.setImageURI(imageUri);

        // Save image URI in SharedPreferences and database
        preferences.edit().putString("image_uri",String.valueOf(imageUri)).apply();

        String email = preferences.getString("email", "");
        db.updateAdminImage(email, String.valueOf(imageUri));



        // Set the result to send edited image to AdminHomeActivity & Sittings
        Intent resultIntent = new Intent();
        resultIntent.putExtra("image_uri", String.valueOf(imageUri));
        setResult(RESULT_OK, resultIntent);
    }

    /** fillOutProfileWithUserData()
     *  fillOut Admin Data (name,email,phone)
     **********************************************************************************************/
    public void fillOutProfileWithUserData(){

        // Retrieve user data from SharedPreferences
        // Populate views with user data
        preferences = getSharedPreferences("userInfo",MODE_PRIVATE);

        String name = preferences.getString("fName", "");
        String email = preferences.getString("email", "");
        String phone = preferences.getString("phoneNumber", "");
        String image_uri = preferences.getString("image_uri", "");
        String password = preferences.getString("password", "");

        tv_name.setText(name);
        tv_email.setText(email);
        et_phone_number.setText(phone);
        et_password.setText(password);
        profileImage.setImageURI(Uri.parse(image_uri));
    }

    /** setBackButtonAction()
     *  Back To The Previous Activity
     **********************************************************************************************/
    public void setBackButtonAction() {
        btn_back.setOnClickListener(v -> onBackPressed());

    }

    /** initializeDialogEditPhoneNumber()
     *  Initialize Dialog Edit PhoneNumber
     **********************************************************************************************/
    public void initializeDialogEditPhoneNumber() {
        dialog_edit_phone_number = new Dialog(AdminProfileActivity.this);
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
        dialog_edit_password = new Dialog(AdminProfileActivity.this);
        dialog_edit_password.setContentView(R.layout.fragment_edit_password_dialoge);
        dialog_edit_password.getWindow().setBackgroundDrawable(getDrawable(custom_profile_dialoge));
        dialog_edit_password.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;

        password_dialog_btn_save = dialog_edit_password.findViewById(R.id.fragment_edit_password_dialog_btn_save);
        password_dialog_btn_cancel = dialog_edit_password.findViewById(R.id.fragment_edit_password_dialog_btn_cansel);

        password_dialog_et_old_password = dialog_edit_password.findViewById(R.id.fragment_edit_password_dialog_et_old_password);
        password_dialog_et_new_password = dialog_edit_password.findViewById(R.id.fragment_edit_password_dialog_et_new_password);
        password_dialog_et_confirm_password = dialog_edit_password.findViewById(R.id.fragment_edit_password_dialog_et_confirm_password);

        layout_old_password = dialog_edit_password.findViewById(R.id.fragment_edit_password_dialog_til_old_password);
        layout_new_password = dialog_edit_password.findViewById(R.id.fragment_edit_password_dialog_til_new_password);
        layout_confirm_password = dialog_edit_password.findViewById(R.id.fragment_edit_password_dialog_til_confirm_password);

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
    public void setEditPasswordButtonAction(){
        btn_edit_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_edit_password.show();
                isPasswordChanged();

            }
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
                    layout_phone_number.setError("Enter a valid phone number");
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


            db.updateAdminPhoneNumber(tv_email.getText().toString(),newPhoneNumber); //update in data base

            preferences.edit().putString("phoneNumber",newPhoneNumber).apply(); //update in shared Preferences userInfo file
            et_phone_number.setText(newPhoneNumber); //update in editText profile

            dialog_edit_phone_number.cancel(); // close dialog

        });
    }
    /** isPasswordChanged()
     *  This method monitors whether the password has changed and sets the save button accordingly.
     **********************************************************************************************/
    private void isPasswordChanged() {
        String oldPassword = preferences.getString("password", "");

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                String newPassword = password_dialog_et_new_password.getText().toString();
                String confirmPassword = password_dialog_et_confirm_password.getText().toString();

                // Validate the password format
                boolean isOldPasswordNotCorrect = !(password_dialog_et_old_password.getText().toString().equals(oldPassword));
                boolean isNewMatchOld = newPassword.equals(oldPassword);
                boolean isPasswordLengthMoreThanFif = newPassword.length() >= 6;
                boolean isValidPassword = newPassword. matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*]).*$");
                boolean isMatchingPasswords = newPassword.equals(confirmPassword);

                //set save button disabled if old,new,confirm any of them is empty
                password_dialog_btn_save.setEnabled(!isOldPasswordNotCorrect && !isNewMatchOld && isPasswordLengthMoreThanFif && isValidPassword && isMatchingPasswords);

                //This For Old Password
                if(isOldPasswordNotCorrect && !password_dialog_et_old_password.hasFocus()){
                    layout_old_password.setError("Wrong password! Please enter the correct old password.");
                    layout_old_password.setHelperText("");
                }
                else if (!isOldPasswordNotCorrect && !password_dialog_et_old_password.hasFocus()){
                    password_dialog_et_old_password.setEnabled(false);
                }
                else if (!isOldPasswordNotCorrect) {
                    layout_old_password.setError("");
                    layout_old_password.setHelperText("");
                }

                //This For New Password
                if(isNewMatchOld) {
                    layout_new_password.setError("Please choose a different password. New password cannot be the same as the old password.");
                    layout_new_password.setHelperText("");
                }
                else if (!isPasswordLengthMoreThanFif && !password_dialog_et_new_password.hasFocus() && !password_dialog_et_old_password.hasFocus()) {
                    layout_new_password.setError("Password must be at least 6 characters long.");
                    layout_new_password.setHelperText("");
                }
                else if (!isValidPassword && !password_dialog_et_new_password.hasFocus() && !password_dialog_et_old_password.hasFocus()) {
                    layout_new_password.setError("Password should contain at least one number, one letter, and one special character (!@#$%^&*)");
                    layout_new_password.setHelperText("");
                }
                else {
                    layout_new_password.setError("");
                    layout_new_password.setHelperText("");
                }

                //This For Confirm Password
                if(!isMatchingPasswords && !password_dialog_et_new_password.hasFocus()){
                    layout_confirm_password.setError("Passwords don't Match!");
                    layout_confirm_password.setHelperText("");
                }
                else {
                    layout_confirm_password.setError("");
                    layout_confirm_password.setHelperText("");
                }



            }
        };

        password_dialog_et_old_password.addTextChangedListener(textWatcher);
        password_dialog_et_new_password.addTextChangedListener(textWatcher);
        password_dialog_et_confirm_password.addTextChangedListener(textWatcher);
    }



    /** setCancelEditedPasswordButtonAction()
     *  Set Cancel Edited Password  Button Action
     **********************************************************************************************/
    public void setCancelEditedPasswordButtonAction () {
        password_dialog_btn_cancel.setOnClickListener(v ->

                dialog_edit_password.cancel());
                password_dialog_et_old_password.getText().clear();
                password_dialog_et_new_password.getText().clear();
                password_dialog_et_confirm_password.getText().clear();

    }


    /** dialog_edit_password_btn_save()
     *  Set Save Edited Password Button Action
     **********************************************************************************************/
    public void setSaveEditedPasswordButtonAction () {
        password_dialog_btn_save.setOnClickListener(v -> {



        });
    }
}