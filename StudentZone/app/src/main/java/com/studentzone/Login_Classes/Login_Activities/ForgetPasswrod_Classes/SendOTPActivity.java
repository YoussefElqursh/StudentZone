package com.studentzone.Login_Classes.Login_Activities.ForgetPasswrod_Classes;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.studentzone.Login_Classes.Login_Activities.LoginActivity;
import com.studentzone.R;

import java.util.ArrayList;

public class SendOTPActivity extends AppCompatActivity {

     String otp = "548247";
     String phoneNumber , email;
     String message = "is your verification code.";
     Button buttonGetOTP;
     Button btn_back;
     EditText inputMobile,inputEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_o_t_p);

        inputMobile = findViewById(R.id.input_PhoneNumber);

        buttonGetOTP = findViewById(R.id.btn_getOTP);

        btn_back = findViewById(R.id.activity_send_otp_btn_back);

        inputEmail = findViewById(R.id.input_Email);

        get_code();
        setBackButtonAction();
    }

    public void get_code()
    {
        buttonGetOTP.setOnClickListener(v -> vaidateNumber());

    }

    public void vaidateNumber()
    {
        email = inputEmail.getText().toString().trim();
        phoneNumber = inputMobile.getText().toString().trim();
        String phonePattern = "^1[0125][0-9]{8}$";
        String emailPattern = "^[\\w.-]+@monufia\\.edu$";

        if(email.matches(emailPattern)){
            if(phoneNumber.matches(phonePattern))
            {
                Intent intent = new Intent(getBaseContext(), VerifyOTPActivity.class);
                intent.putExtra("mobile",inputMobile.getText().toString());
                intent.putExtra("OTP",otp);
                startActivity(intent);

                permissionIsGranted();
            }
            else
            {
                if(phoneNumber.isEmpty())
                {
                    Toast.makeText(SendOTPActivity.this, "Mobile Number is empty ", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(SendOTPActivity.this, "Enter correct Mobile Number", Toast.LENGTH_LONG).show();
                }
            }
        }
        else if(phoneNumber.matches(phonePattern))
        {
            if(!email.matches(emailPattern))
            {
                if(email.isEmpty())
                {
                    Toast.makeText(SendOTPActivity.this, "Email is empty ", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(SendOTPActivity.this, "Enter correct Email", Toast.LENGTH_LONG).show();
                }
            }
        }
        else if(phoneNumber.isEmpty() && email.isEmpty())
        {
                Toast.makeText(SendOTPActivity.this, "Mobile Number and Email is empty ", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(SendOTPActivity.this, "All of Email and Mobile Number is wrong ", Toast.LENGTH_LONG).show();
        }
    }

    public void permissionIsGranted()
    {
        if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
        {
            send_OTP();
        }
        else
        {
            ActivityCompat.requestPermissions(SendOTPActivity.this,new String[]{Manifest.permission.SEND_SMS}, 100);
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 100 )
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                send_OTP();
            }
            else
            {
                Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void send_OTP()
    {
        phoneNumber = inputMobile.getText().toString();

        SmsManager smsManager = SmsManager.getDefault();

        ArrayList<String> parts = smsManager.divideMessage(otp+" "+message);

        String phone = phoneNumber;

        smsManager.sendMultipartTextMessage(phone,null,parts,null,null);

    }


    /** setBackButtonAction()
     *  Back To The Previous Activity
     **********************************************************************************************/
    public void setBackButtonAction(){
        btn_back.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), LoginActivity.class)));
    }
}