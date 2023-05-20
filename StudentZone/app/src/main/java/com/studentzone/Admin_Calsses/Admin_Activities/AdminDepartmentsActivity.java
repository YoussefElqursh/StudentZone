package com.studentzone.Admin_Calsses.Admin_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Admin_Calsses.Admin_Fragments.AdminNewDepartmentFragment;
import com.studentzone.Admin_Calsses.Admin_Models.Admin_Department_Model.DepartmentAdaper;
import com.studentzone.Admin_Calsses.Admin_Models.Admin_Department_Model.DepartmentModel;
import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.SubjectAdapter;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.SubjectModel;

import java.util.ArrayList;
import java.util.List;

public class AdminDepartmentsActivity extends AppCompatActivity  {
    Button btn_add, btn_back, btm_sheet_dia_btn_save, btm_sheet_dia_btn_close;
    EditText btm_sheet_dia_et_dept_name, btm_sheet_dia_et_dept_code;
    BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_departments);

//        btn_add = findViewById(R.id.activity_admin_depatrments_btn_add);
//        bottomSheetDialog = new BottomSheetDialog(AdminDepartmentsActivity.this, R.style.BottomSheetStyle);
//
//        buttonAddAction();
//        btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottomSheetDialog.show();
//            }
//        });


        buttonBackAction();
        DoctorDepartmentRecyclerView();
    }

    // This function to show and hide bottomSheetDialog //
    public void buttonAddAction() {
//        btn_add = findViewById(R.id.activity_admin_depatrments_btn_add);
//        btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(AdminDepartmentsActivity.this, R.style.BottomSheetStyle);
//                View bottomSheetDialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.fragment_admin_new_department, (LinearLayout)findViewById(R.id.fragment_admin_new_department_ll_main));
//                bottomSheetDialogView.findViewById(R.id.fragment_admin_new_department_btn_close).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        bottomSheetDialog.dismiss();
//                    }
//                });
//                bottomSheetDialogView.findViewById(R.id.fragment_new_department_btn_save).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        bottomSheetDialog.dismiss();
//                    }
//                });
//                bottomSheetDialog.setContentView(bottomSheetDialogView);
//                bottomSheetDialog.show();
//            }
//        });

//        View bottomSheetDialogView = getLayoutInflater().inflate(R.layout.fragment_admin_new_department, null, false);
//
//        Button btn_save = bottomSheetDialogView.findViewById(R.id.fragment_new_department_btn_save);
//        EditText et_department_name = bottomSheetDialogView.findViewById(R.id.fragment_new_department_et_course_name);
//
//        btn_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottomSheetDialog.dismiss();
//                Toast.makeText(AdminDepartmentsActivity.this, et_department_name.getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        bottomSheetDialog.setContentView(bottomSheetDialogView);

    }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_admin_departments_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(AdminDepartmentsActivity.this,AdminHomeActivity.class)));
    }

    public RecyclerView DoctorDepartmentRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.activity_admin_departments_recycelerview);

        List<DepartmentModel> departmentModel = new ArrayList<DepartmentModel>();
        departmentModel.add(new DepartmentModel("os","bad",R.drawable.ic_department_1));
        departmentModel.add(new DepartmentModel("os","bad",R.drawable.ic_department_1));
        departmentModel.add(new DepartmentModel("os","bad",R.drawable.ic_department_1));
        departmentModel.add(new DepartmentModel("os","bad",R.drawable.ic_department_1));
        departmentModel.add(new DepartmentModel("os","bad",R.drawable.ic_department_1));
        departmentModel.add(new DepartmentModel("os","bad",R.drawable.ic_department_1));
        departmentModel.add(new DepartmentModel("os","bad",R.drawable.ic_department_1));
        departmentModel.add(new DepartmentModel("os","bad",R.drawable.ic_department_1));
        departmentModel.add(new DepartmentModel("os","bad",R.drawable.ic_department_1));
        departmentModel.add(new DepartmentModel("os","bad",R.drawable.ic_department_1));
        departmentModel.add(new DepartmentModel("os","bad",R.drawable.ic_department_1));
        departmentModel.add(new DepartmentModel("os","bad",R.drawable.ic_department_1));
        departmentModel.add(new DepartmentModel("os","bad",R.drawable.ic_department_1));
        departmentModel.add(new DepartmentModel("os","bad",R.drawable.ic_department_1));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DepartmentAdaper(getApplicationContext(),departmentModel));
        return recyclerView;
    }
}