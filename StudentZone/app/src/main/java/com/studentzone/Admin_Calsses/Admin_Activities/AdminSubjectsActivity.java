package com.studentzone.Admin_Calsses.Admin_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
//import com.studentzone.Admin_Calsses.Admin_Models.Admin_Department_Model.AdminDepartmentAdaper;
//import com.studentzone.Admin_Calsses.Admin_Models.Admin_Department_Model.AdminDepartmentModel;
//import com.studentzone.Admin_Calsses.Admin_Models.Admin_Subject_Model.AdminSubjectAdapter;
//import com.studentzone.Admin_Calsses.Admin_Models.Admin_Subject_Model.AdminSubjectModel;
import com.studentzone.R;

import java.util.ArrayList;
import java.util.List;

public class AdminSubjectsActivity extends AppCompatActivity {
    Button btn_add, btn_back ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_subjects);
        buttonAddAction();
        buttonBackAction();
    }

    // This function to show and hide bottomSheetDialog //
    public void buttonAddAction() {
        btn_add = findViewById(R.id.activity_admin_subjects_btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(AdminSubjectsActivity.this, R.style.BottomSheetStyle);
                View bottomSheetDialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.fragment_admin_new_subject, (LinearLayout)findViewById(R.id.fragment_admin_new_subject_ll_main));
                bottomSheetDialogView.findViewById(R.id.fragment_admin_new_subject_btn_close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialogView.findViewById(R.id.fragment_new_subject_btn_save).setOnClickListener(new View.OnClickListener() {
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
        btn_back = findViewById(R.id.activity_admin_subjects_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(AdminSubjectsActivity.this,AdminHomeActivity.class)));
    }
}