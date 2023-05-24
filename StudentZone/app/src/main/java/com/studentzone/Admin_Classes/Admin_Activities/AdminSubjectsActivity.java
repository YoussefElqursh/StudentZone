package com.studentzone.Admin_Classes.Admin_Activities;

import android.text.TextUtils;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
//import com.studentzone.Admin_Calsses.Admin_Models.Admin_Department_Model.AdminDepartmentAdaper;
//import com.studentzone.Admin_Calsses.Admin_Models.Admin_Department_Model.AdminDepartmentModel;
//import com.studentzone.Admin_Calsses.Admin_Models.Admin_Subject_Model.AdminSubjectAdapter;
//import com.studentzone.Admin_Calsses.Admin_Models.Admin_Subject_Model.AdminSubjectModel;
import com.studentzone.Admin_Classes.Admin_Models.subjectRecyclerViewAdapter;
import com.studentzone.Data_Base.Courses;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;

import java.util.ArrayList;

public class AdminSubjectsActivity extends AppCompatActivity {
    My_DB db = new My_DB(this);
    Button btn_add, btn_back, btm_sheet_dia_btn_save, btm_sheet_dia_btn_close;
    EditText btm_sheet_dia_et_sub_name, btm_sheet_dia_et_sub_code, btm_sheet_dia_et_subject_department, btm_sheet_dia_et_subject_doctor, btm_sheet_dia_et_subject_previous;
    BottomSheetDialog bottomSheetDialog;
    RecyclerView rv;
    View bottomSheetDialogView;
    String name, code, department, doctor, previous_subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_subjects);


        btn_add = findViewById(R.id.activity_admin_subjects_btn_add);
        bottomSheetDialog = new BottomSheetDialog(AdminSubjectsActivity.this, R.style.BottomSheetStyle);

        btn_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                buttonAddAction();

            }
        });
        inflate();
        buttonBackAction();
        showAllSubjects();
    }

    public void inflate() {
        btn_add = findViewById(R.id.activity_admin_subjects_btn_add);
        btn_back = findViewById(R.id.activity_admin_subjects_btn_back);

        bottomSheetDialog = new BottomSheetDialog(AdminSubjectsActivity.this, R.style.BottomSheetStyle);
        bottomSheetDialogView = getLayoutInflater().inflate(R.layout.fragment_admin_new_subject, null, false);

        btm_sheet_dia_btn_save = bottomSheetDialogView.findViewById(R.id.fragment_new_subject_btn_save);
        btm_sheet_dia_btn_close = bottomSheetDialogView.findViewById(R.id.fragment_admin_new_subject_btn_close);

        btm_sheet_dia_et_sub_name = bottomSheetDialogView.findViewById(R.id.fragment_new_subject_et_name);
        btm_sheet_dia_et_sub_code = bottomSheetDialogView.findViewById(R.id.fragment_new_subject_et_code);
        btm_sheet_dia_et_subject_department = bottomSheetDialogView.findViewById(R.id.fragment_new_subject_et_department);
        btm_sheet_dia_et_subject_doctor = bottomSheetDialogView.findViewById(R.id.fragment_new_subject_et_doctor);
        btm_sheet_dia_et_subject_previous = bottomSheetDialogView.findViewById(R.id.fragment_new_subject_et_previous);

        rv = findViewById(R.id.activity_admin_subjects_recycleView);
    }

    public void buttonAddAction() {
        View bottomSheetDialogView = getLayoutInflater().inflate(R.layout.fragment_admin_new_subject, null, false);
        Button btn_save = bottomSheetDialogView.findViewById(R.id.fragment_new_subject_btn_save);
        Button  btm_sheet_dia_btn_close = bottomSheetDialogView.findViewById(R.id.fragment_admin_new_subject_btn_close);


        btm_sheet_dia_et_sub_name = bottomSheetDialogView.findViewById(R.id.fragment_new_subject_et_name);
        btm_sheet_dia_et_sub_code = bottomSheetDialogView.findViewById(R.id.fragment_new_subject_et_code);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = btm_sheet_dia_et_sub_name.getText().toString();
                String code = btm_sheet_dia_et_sub_code.getText().toString();
                String department = btm_sheet_dia_et_subject_department.getText().toString();
                String doctor = btm_sheet_dia_et_subject_doctor.getText().toString();
                String prev_subject = btm_sheet_dia_et_subject_previous.getText().toString();

                db.insert_subject(name, code, department, doctor, prev_subject);

                showAllSubjects();

                btm_sheet_dia_et_sub_name.setText("");
                btm_sheet_dia_et_sub_code.setText("");
                btm_sheet_dia_et_subject_department.setText("");
                btm_sheet_dia_et_subject_doctor.setText("");
                btm_sheet_dia_et_subject_previous.setText("");
                bottomSheetDialog.dismiss();
            }
        });

        btm_sheet_dia_btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();

            }
        });

        bottomSheetDialog.setContentView(bottomSheetDialogView);
        bottomSheetDialog.show();

    }

    // This function to show and hide bottomSheetDialog //

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_admin_subjects_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(AdminSubjectsActivity.this,AdminHomeActivity.class)));
    }

    public void showAllSubjects() {

        My_DB db = new My_DB(getBaseContext());


        ArrayList<Courses> coursesArrayList = db.showCourses();

        subjectRecyclerViewAdapter adapter = new subjectRecyclerViewAdapter(coursesArrayList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
    }

}