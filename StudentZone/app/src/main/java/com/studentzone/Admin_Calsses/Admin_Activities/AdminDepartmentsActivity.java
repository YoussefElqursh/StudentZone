package com.studentzone.Admin_Calsses.Admin_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.R;

public class AdminDepartmentsActivity extends AppCompatActivity {
    Button activity_admin_departments_btn_add_new_department;

    Button buttonBack ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_departments);
        buttonBack = findViewById(R.id.activity_admin_depatrments_btn_back);
        buttonBack.setOnClickListener(v -> startActivity(new Intent(AdminDepartmentsActivity.this,AdminHomeActivity.class)));
        showBottomSheetDialog();
    }

    public void showBottomSheetDialog() {
        activity_admin_departments_btn_add_new_department = findViewById(R.id.activity_admin_depatrments_btn_add);
        activity_admin_departments_btn_add_new_department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(AdminDepartmentsActivity.this, R.style.BottomSheetStyle);
                View bottomSheetDialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.fragment_admin_new_department, (LinearLayout)findViewById(R.id.fragment_admin_new_department_ll_main));
                bottomSheetDialogView.findViewById(R.id.fragment_admin_new_department_btn_close).setOnClickListener(new View.OnClickListener() {
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
}