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
import com.studentzone.Admin_Calsses.Admin_Models.Admin_Doctors_Accounts_Model.AdminDoctorAccountAdapter;
import com.studentzone.Admin_Calsses.Admin_Models.Admin_Doctors_Accounts_Model.AdminDoctorAccountModel;
import com.studentzone.Admin_Calsses.Admin_Models.Admin_Subject_and_AbcenceFile_Model.AdminSubjectAdapter;
import com.studentzone.Admin_Calsses.Admin_Models.Admin_Subject_and_AbcenceFile_Model.AdminSubjectModel;
import com.studentzone.R;

import java.util.ArrayList;
import java.util.List;

public class AdminDoctorsAccountsActivity extends AppCompatActivity {
    Button btn_add, btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_doctors_accounts);
        buttonAddAction();
        buttonBackAction();
        AdminDoctorAccountsRecyclerView();
    }

    // This function to show and hide bottomSheetDialog //
    public void buttonAddAction() {
        btn_add = findViewById(R.id.activity_admin_doctors_accounts_btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(AdminDoctorsAccountsActivity.this, R.style.BottomSheetStyle);
                View bottomSheetDialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.fragment_admin_new_doctor_account, (LinearLayout)findViewById(R.id.fragment_admin_new_doctor_ll_main));
                bottomSheetDialogView.findViewById(R.id.fragment_admin_new_doctor_btn_close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialogView.findViewById(R.id.fragment_new_doctor_btn_save).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetDialogView);
                bottomSheetDialog.show();
                bottomSheetDialog.setContentView(bottomSheetDialogView);
                bottomSheetDialog.show();
            }
        });
    }
    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_admin_doctors_accounts_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(AdminDoctorsAccountsActivity.this,AdminHomeActivity.class)));
    }

    public RecyclerView AdminDoctorAccountsRecyclerView()
    {
        RecyclerView recyclerView = findViewById(R.id.activity_admin_doctors_accounts_recyclerview);

        List<AdminDoctorAccountModel> adminDoctorAccountModel = new ArrayList<AdminDoctorAccountModel>();
        adminDoctorAccountModel.add(new AdminDoctorAccountModel("os","bad",R.drawable.ic_male_doctor));
        adminDoctorAccountModel.add(new AdminDoctorAccountModel("os","bad",R.drawable.ic_male_doctor));
        adminDoctorAccountModel.add(new AdminDoctorAccountModel("os","bad",R.drawable.ic_male_doctor));
        adminDoctorAccountModel.add(new AdminDoctorAccountModel("os","bad",R.drawable.ic_male_doctor));
        adminDoctorAccountModel.add(new AdminDoctorAccountModel("os","bad",R.drawable.ic_male_doctor));
        adminDoctorAccountModel.add(new AdminDoctorAccountModel("os","bad",R.drawable.ic_male_doctor));
        adminDoctorAccountModel.add(new AdminDoctorAccountModel("os","bad",R.drawable.ic_male_doctor));
        adminDoctorAccountModel.add(new AdminDoctorAccountModel("os","bad",R.drawable.ic_male_doctor));
        adminDoctorAccountModel.add(new AdminDoctorAccountModel("os","bad",R.drawable.ic_male_doctor));
        adminDoctorAccountModel.add(new AdminDoctorAccountModel("os","bad",R.drawable.ic_male_doctor));
        adminDoctorAccountModel.add(new AdminDoctorAccountModel("os","bad",R.drawable.ic_male_doctor));
        adminDoctorAccountModel.add(new AdminDoctorAccountModel("os","bad",R.drawable.ic_male_doctor));
        adminDoctorAccountModel.add(new AdminDoctorAccountModel("os","bad",R.drawable.ic_male_doctor));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdminDoctorAccountAdapter(getApplicationContext(),adminDoctorAccountModel));
        return recyclerView;
    }

}