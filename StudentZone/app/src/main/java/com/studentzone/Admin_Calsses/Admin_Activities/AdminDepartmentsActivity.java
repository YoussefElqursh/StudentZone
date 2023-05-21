package com.studentzone.Admin_Calsses.Admin_Activities;
import android.annotation.SuppressLint;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Admin_Calsses.Admin_Models.Admin_Department_Model.departmentRecyclerViewAdapter;
import com.studentzone.Data_Base.Departments;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;

import java.util.ArrayList;

public class AdminDepartmentsActivity extends AppCompatActivity  {
    My_DB db = new My_DB(this);
    Button btn_add, btn_back, btm_sheet_dia_btn_save, btm_sheet_dia_btn_close;
    EditText btm_sheet_dia_et_dept_name, btm_sheet_dia_et_dept_code;
    BottomSheetDialog bottomSheetDialog;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_departments);


        btn_add = findViewById(R.id.activity_admin_depatrments_btn_add);
        bottomSheetDialog = new BottomSheetDialog(AdminDepartmentsActivity.this, R.style.BottomSheetStyle);
        buttonAddAction();

        btn_add.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                bottomSheetDialog.show();
            }
        });
        buttonBackAction();
        showAllDepartments();

    }

    // This function to show and hide bottomSheetDialog //
    public void buttonAddAction() {
        View bottomSheetDialogView = getLayoutInflater().inflate(R.layout.fragment_admin_new_department,null, false);

        btm_sheet_dia_btn_save = bottomSheetDialogView.findViewById(R.id.fragment_new_department_btn_save);
        btm_sheet_dia_btn_close = bottomSheetDialogView.findViewById(R.id.fragment_admin_new_department_btn_close);

        btm_sheet_dia_et_dept_name = bottomSheetDialogView.findViewById(R.id.fragment_new_department_et_course_name);
        btm_sheet_dia_et_dept_code = bottomSheetDialogView.findViewById(R.id.fragment_new_department_et_course_code);

        // the action of save button to add new department in bottom sheet
        btm_sheet_dia_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = btm_sheet_dia_et_dept_name.getText().toString();
                String code = btm_sheet_dia_et_dept_code.getText().toString();

                db.insert_department(name, code);

                btm_sheet_dia_et_dept_name.setText("");
                btm_sheet_dia_et_dept_code.setText("");

                showAllDepartments();
                bottomSheetDialog.dismiss();
            }
        });

        // the action of close button in bottom sheet
        btm_sheet_dia_btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(bottomSheetDialogView);
    }

    //the button to return to admin home page
    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_admin_departments_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(AdminDepartmentsActivity.this,AdminHomeActivity.class)));
    }

    public void showAllDepartments()
    {
        My_DB myDb = new My_DB(getBaseContext());

        ArrayList<Departments> departmentsArrayList = db.showDepartments();

        departmentRecyclerViewAdapter adapter = new departmentRecyclerViewAdapter(departmentsArrayList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        RecyclerView rv  = findViewById(R.id.activity_admin_departments_recycelerview);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
    }
}