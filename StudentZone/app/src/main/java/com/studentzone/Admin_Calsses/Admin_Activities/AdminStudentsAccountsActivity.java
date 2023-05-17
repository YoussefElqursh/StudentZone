package com.studentzone.Admin_Calsses.Admin_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.R;

public class AdminStudentsAccountsActivity extends AppCompatActivity {
    Button activity_admin_students_accounts_btn_add, activity_admin_students_accounts_btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_students_accounts);
        showAndHideBottomSheetDialog();
        buttonBackAction();
    }

    public void showAndHideBottomSheetDialog() {
        activity_admin_students_accounts_btn_add = findViewById(R.id.activity_admin_students_accounts_btn_add);
        activity_admin_students_accounts_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(AdminStudentsAccountsActivity.this, R.style.BottomSheetStyle);
                View bottomSheetDialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.fragment_admin_new_student_account, (LinearLayout)findViewById(R.id.fragment_admin_new_student_ll_main));
                bottomSheetDialogView.findViewById(R.id.fragment_admin_new_student_btn_close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialogView.findViewById(R.id.fragment_new_student_btn_save).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetDialogView);
                bottomSheetDialog.show();
            }
        });
    }

    public void buttonBackAction(){
        activity_admin_students_accounts_btn_back = findViewById(R.id.activity_admin_students_accounts_btn_back);
        activity_admin_students_accounts_btn_back.setOnClickListener(v -> startActivity(new Intent(AdminStudentsAccountsActivity.this,AdminHomeActivity.class)));
    }
}