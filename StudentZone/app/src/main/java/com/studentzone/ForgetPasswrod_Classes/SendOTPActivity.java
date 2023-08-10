package com.studentzone.ForgetPasswrod_Classes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.studentzone.Admin_Classes.Admin_Activities.AdminHomeActivity;
import com.studentzone.Login_Classes.Login_Activities.LoginActivity;
import com.studentzone.R;

public class SendOTPActivity extends AppCompatActivity {

     Button btn_back;

    EditText inputMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_o_t_p);

        send_code();
        setBackButtonAction();
    }
    public void send_code()
    {

        inputMobile = findViewById(R.id.input_PhoneNumber);

        Button buttonGetOTP = findViewById(R.id.btn_getOTP);

        buttonGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(inputMobile.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(SendOTPActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getBaseContext(), VerifyOTPActivity.class);
                intent.putExtra("mobile",inputMobile.getText().toString());
                startActivity(intent);
            }
        });

    }


    /** setBackButtonAction()
     *  Back To The Previous Activity
     **********************************************************************************************/
    public void setBackButtonAction(){
        btn_back = findViewById(R.id.activity_send_otp_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), LoginActivity.class)));
    }
}