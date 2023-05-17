package com.studentzone.Login_Classes.Login_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.studentzone.Admin_Calsses.Admin_Activities.AdminHomeActivity;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.Doctor_Classes.Doctor_Activities.DoctorSubjectPDFsActivity;
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
    My_DB db = new My_DB(this);
    int kindCheckedId = -1;


    /*Inflate
    ***********************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.activity_login_btn_login);
        et_user_name = findViewById(R.id.activity_login_tf_username);
        et_password = findViewById(R.id.activity_login_tf_password);
        rg_user_kind = findViewById(R.id.activity_login_rg_user_kind);
        rb_admin = findViewById(R.id.activity_login_rb_admin);
        rb_student = findViewById(R.id.activity_login_rb_student);
        rb_doctor = findViewById(R.id.activity_login_rb_doctor);

        /*Get Index Of Radiobutton
        *******************************************************************************************/
        rg_user_kind.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                kindCheckedId = group.indexOfChild(findViewById(checkedId));

//                if (checkedId != -1) {
//                    btn_login.setEnabled(true);
//                } else {
//                    btn_login.setEnabled(false);
//                    Toast.makeText(getApplicationContext(), "Please, Choose an User Kind.", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        /*Login Button
        *******************************************************************************************/
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isValid(kindCheckedId, et_user_name.getText().toString(), et_password.getText().toString());
            }
        });
     }

    /*isValid()
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
            intent = new Intent(LoginActivity.this, DoctorSubjectPDFsActivity.class);
            startActivity(intent);
            finish();
        }
        else if (db.checkLogin(checkedKind, email, password) && checkedKind == 2) {
            intent = new Intent(LoginActivity.this, StudentHomeActivity.class);
            startActivity(intent);
            finish();
        }
        else
            Toast.makeText(this, "Invalid Username Or Password !", Toast.LENGTH_SHORT).show();

        return intent;

    }

}