package com.studentzone.ForgetPasswrod_Classes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.studentzone.Login_Classes.Login_Activities.LoginActivity;
import com.studentzone.R;

import java.util.ArrayList;

public class SendOTPActivity extends AppCompatActivity {

     String otp = "548247";
     String phoneNumber ;
     String message = "is your verification code.";
     Button buttonGetOTP;
     Button btn_back;
     EditText inputMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_o_t_p);

        inputMobile = findViewById(R.id.input_PhoneNumber);

        buttonGetOTP = findViewById(R.id.btn_getOTP);

        btn_back = findViewById(R.id.activity_send_otp_btn_back);

        get_code();
        setBackButtonAction();
    }
    public void get_code()
    {
        buttonGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaidateNumber();
            }
        });

    }

    public void vaidateNumber()
    {
        phoneNumber = inputMobile.getText().toString().trim();
        String phonePattern = "^01[0-2][0-9]{8}$";

        if(phoneNumber.matches(phonePattern)){
            Intent intent = new Intent(getBaseContext(), VerifyOTPActivity.class);
            intent.putExtra("mobile",inputMobile.getText().toString());
            startActivity(intent);

            permissionIsGranted();

        }
        else if(inputMobile.getText().toString().trim().isEmpty())
        {
            Toast.makeText(SendOTPActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(SendOTPActivity.this, "Enter correct Mobile Number", Toast.LENGTH_LONG).show();
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