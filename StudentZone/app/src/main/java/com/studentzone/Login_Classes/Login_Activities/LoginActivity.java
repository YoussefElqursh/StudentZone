package com.studentzone.Login_Classes.Login_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.studentzone.Data_Base.Admins;
import com.studentzone.Doctor_Classes.Doctor_Activities.DoctorSubjectPdfsActivity;
import com.studentzone.Admin_Calsses.Admin_Activities.AdminHomeActivity;
import com.studentzone.Student_Classes.Student_Activities.StudentHomeActivity;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Activities.StudentHomeActivity;


public class LoginActivity extends AppCompatActivity {
    Button btn_login;
    EditText et_user_name;
    EditText et_password;
    RadioGroup rg_user_kind;
    RadioButton rb_admin;
    RadioButton rb_doctor;
    RadioButton rb_student;
    CheckBox cb_Remember_me;
    My_DB db = new My_DB(this);
    int kindCheckedId = -1;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        /** Inflate
         ******************************************************************************************/
        btn_login = findViewById(R.id.activity_login_btn_login);
        et_user_name = findViewById(R.id.activity_login_tf_username);
        et_password = findViewById(R.id.activity_login_tf_password);
        rg_user_kind = findViewById(R.id.activity_login_rg_user_kind);
        rb_admin = findViewById(R.id.activity_login_rb_admin);
        rb_student = findViewById(R.id.activity_login_rb_student);
        rb_doctor = findViewById(R.id.activity_login_rb_doctor);
        cb_Remember_me = findViewById(R.id.activity_login_cb_rememberme);


        /** This is The File (Login_Prefs) Store The Values Of RememberMe(username,pass,kind) Which
         * Located In Shred Preferences Folder.
         * Variables In This File(username,password,kind) Have Default Values ("","",-1).
        / ******************************************************************************************/
        preferences = getSharedPreferences("Login_Prefs", MODE_PRIVATE);
        String savedUsername = preferences.getString("username", "");
        String savedPassword = preferences.getString("password", "");
        int kind = preferences.getInt("kind", -1);

        if (!savedUsername.isEmpty() && !savedPassword.isEmpty() && kind != -1) {
            et_user_name.setText(savedUsername);
            et_password.setText(savedPassword);
            kindCheckedId = kind;
            cb_Remember_me.setChecked(true);

        }

        /**RadioButtonGroup
         *kindCheckedId, Get Index Of Checked User Kind in
         ******************************************************************************************/
        rg_user_kind.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                kindCheckedId = group.indexOfChild(findViewById(checkedId));

            }
        });

        
        /** ButtonLogin
         ******************************************************************************************/
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isValid(kindCheckedId, et_user_name.getText().toString(), et_password.getText().toString());
                rememberMe();
            }
        });

    }


    /**isValid()
     * This Method Takes Three parameters
     * @param checkedKind (none or Admin or Doctor or Student)
     * @param email       The Email_et
     * @param password    The Password_et
     * It's Return Intent (Admin or Doctor or Student) UpOn Checked Radiobutton
     **********************************************************************************************/
    public Intent isValid(int checkedKind, String email, String password) {

        Intent intent = null;

        if (checkedKind == -1)
            Toast.makeText(this, "Choose User Kind ,Please !", Toast.LENGTH_SHORT).show();

        else if (db.checkLogin(checkedKind, email, password) && checkedKind == 0) {
            intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
            startActivity(intent);
            finish();
        }
        else if (db.checkLogin(checkedKind, email, password) && checkedKind == 1) {
            intent = new Intent(LoginActivity.this, DoctorSubjectPdfsActivity.class);
            startActivity(intent);
            finish();
        }
        else if (db.checkLogin(checkedKind, email, password) && checkedKind == 2) {
            intent = new Intent(LoginActivity.this, StudentHomeActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "Invalid Username Or Password !", Toast.LENGTH_SHORT).show();
            et_user_name.setText("");
            et_password.setText("");
            cb_Remember_me.setChecked(false);
        }

        return intent;
    }



    /**rememberMe()
     * This Method Return True Or False, It's UpOn The User Checked CheckBox which called remember
     * Me Or Unchecked It.
     * If User Checked It The Code Will Get Three values (kind,email,pass) From the edite Text And
     * Radiobutton,Then Store Them In Three Variables(username,password,kind) In The Login_prefs file.
     * If User Unchecked it Code Will Store ("","",-1) in the Three Values In The Login_Prefs file.
     *  remember_me Will Store True Or False And Finally The Methode Will It.
     **********************************************************************************************/
    public boolean rememberMe(){
        boolean remember_me = false;
        if (cb_Remember_me.isChecked()) {
            String username = et_user_name.getText().toString();
            String password = et_password.getText().toString();
            int kind = kindCheckedId;

            editor = preferences.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.putInt("kind",kind);
            editor.apply();

            remember_me = true;
        }
        else if(!cb_Remember_me.isChecked()) {
            editor = preferences.edit();
            editor.putString("username", "");
            editor.putString("password", "");
            editor.putInt("kind",-1);
            editor.apply();

            remember_me = false;
        }
        return remember_me;
    }

}

