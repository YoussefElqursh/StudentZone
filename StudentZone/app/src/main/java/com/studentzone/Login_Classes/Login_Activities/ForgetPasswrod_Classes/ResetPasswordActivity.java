package com.studentzone.Login_Classes.Login_Activities.ForgetPasswrod_Classes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.studentzone.R;

public class ResetPasswordActivity extends AppCompatActivity {

    Button resetPassword ,btn_back;
    EditText newPassword , confirmationPassword ;
    String NewPassword , ConfirmationPassword ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        btn_back = findViewById(R.id.activity_reset_password_btn_back);

        resetPassword = findViewById(R.id.btn_resetPassword);

        newPassword = findViewById(R.id.activity_reset_password_tf_new_password);

        confirmationPassword = findViewById(R.id.activity_reset_password_tf_confirmation_password);

        ChangePassword();
        setBackButtonAction();
    }
    public void ChangePassword()
    {
        resetPassword.setOnClickListener(v -> validateNewPassword());
    }
    public void validateNewPassword()
    {
        NewPassword = newPassword.getText().toString().trim();

        ConfirmationPassword = confirmationPassword.getText().toString().trim();

        if (NewPassword.equals(ConfirmationPassword) && !(NewPassword.isEmpty() && ConfirmationPassword.isEmpty() ))
        {
            Intent intent = new Intent(ResetPasswordActivity.this, VerifiedActivity.class);
            startActivity(intent);
        }
        else if(NewPassword.isEmpty())
        {
            if (ConfirmationPassword.isEmpty())
            {
                Toast.makeText(ResetPasswordActivity.this, "New Password and Confirmation Password is Empty", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(ResetPasswordActivity.this, "New Password is Empty", Toast.LENGTH_LONG).show();
            }
        }
        else if (ConfirmationPassword.isEmpty())
        {
            if (NewPassword.isEmpty())
            {
                Toast.makeText(ResetPasswordActivity.this, "New Password and Confirmation Password is Empty", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(ResetPasswordActivity.this, "Confirmation Password is Empty", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(ResetPasswordActivity.this, "Two Passwords are not Same", Toast.LENGTH_LONG).show();
        }
    }

    /** setBackButtonAction()
     *  Back To The Previous Activity
     **********************************************************************************************/
    public void setBackButtonAction(){
        btn_back.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), SendOTPActivity.class)));
    }

}