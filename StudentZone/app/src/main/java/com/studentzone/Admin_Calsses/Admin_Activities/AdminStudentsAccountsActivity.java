package com.studentzone.Admin_Calsses.Admin_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.R;

public class AdminStudentsAccountsActivity extends AppCompatActivity {
    Button btn_add, btn_back, btm_sheet_dia_btn_save, btm_sheet_dia_btn_close;
    EditText btm_sheet_dia_et_dept_name, btm_sheet_dia_et_dept_password, btm_sheet_dia_et_dept_email, btm_sheet_dia_et_dept_aid;
    RadioGroup btm_sheet_dia_rg;
    RadioButton btm_sheet_dia_rb_male, btm_sheet_dia_rb_female;
    BottomSheetDialog bottomSheetDialog;
    View bottomSheetDialogView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_students_accounts);
        inflate();
        buttonAddAction();
        buttonBackAction();
    }

    public void inflate(){
        btn_add = findViewById(R.id.activity_admin_students_accounts_btn_add);
        btn_back = findViewById(R.id.activity_admin_students_accounts_btn_back);

        bottomSheetDialog = new BottomSheetDialog(AdminStudentsAccountsActivity.this, R.style.BottomSheetStyle);
        bottomSheetDialogView = getLayoutInflater().inflate(R.layout.fragment_admin_new_student_account, null, false);

        btm_sheet_dia_btn_save = bottomSheetDialogView.findViewById(R.id.fragment_new_student_btn_save);
        btm_sheet_dia_btn_close = bottomSheetDialogView.findViewById(R.id.fragment_admin_new_student_btn_close);

        btm_sheet_dia_et_dept_name = bottomSheetDialogView.findViewById(R.id.fragment_new_student_et_studet_name);
        btm_sheet_dia_et_dept_aid = bottomSheetDialogView.findViewById(R.id.fragment_new_student_et_student_aid);
        btm_sheet_dia_et_dept_password = bottomSheetDialogView.findViewById(R.id.fragment_new_student_et_student_password);
        btm_sheet_dia_et_dept_email = bottomSheetDialogView.findViewById(R.id.fragment_new_student_et_student_email);

        btm_sheet_dia_rg = bottomSheetDialogView.findViewById(R.id.fragment_new_student_rg_student_kind);
        btm_sheet_dia_rb_male = bottomSheetDialogView.findViewById(R.id.fragment_new_student_rb_male);
        btm_sheet_dia_rb_female = bottomSheetDialogView.findViewById(R.id.fragment_new_student_rb_female);
    }

    // This function to show and hide bottomSheetDialog //
    public void buttonAddAction() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btm_sheet_dia_btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                btm_sheet_dia_btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        Toast.makeText(getBaseContext(), btm_sheet_dia_et_dept_name.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetDialogView);
                bottomSheetDialog.show();
            }
        });
    }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_admin_students_accounts_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(AdminStudentsAccountsActivity.this,AdminHomeActivity.class)));
    }

}