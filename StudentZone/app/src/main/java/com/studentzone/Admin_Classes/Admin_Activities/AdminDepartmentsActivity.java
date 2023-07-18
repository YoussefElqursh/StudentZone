package com.studentzone.Admin_Classes.Admin_Activities;
import android.annotation.SuppressLint;

import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;


import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Admin_Classes.Admin_Models.departmentRecyclerViewAdapter;
import com.studentzone.Data_Base.Departments;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;
import java.util.ArrayList;

public class AdminDepartmentsActivity extends AppCompatActivity  {
    My_DB db = new My_DB(this);




     RecyclerView recyclerView;
     departmentRecyclerViewAdapter adapter;
     ArrayList<Departments> DepartmentNames;

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
        DepartmentNames = new ArrayList<>();
        adapter = new departmentRecyclerViewAdapter(DepartmentNames);





    }

    // This function to show and hide bottomSheetDialog //
    public void buttonAddAction() {
        View bottomSheetDialogView = getLayoutInflater().inflate(R.layout.fragment_admin_new_department,null, false);

        Button btn_save = bottomSheetDialogView.findViewById(R.id.fragment_new_department_btn_save);

        btm_sheet_dia_et_dept_name = bottomSheetDialogView.findViewById(R.id.fragment_new_department_et_course_name);
        btm_sheet_dia_et_dept_code = bottomSheetDialogView.findViewById(R.id.fragment_new_department_et_course_code);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Saved Successfully ✔️", Toast.LENGTH_SHORT).show();

                String name = btm_sheet_dia_et_dept_name.getText().toString();
                String code = btm_sheet_dia_et_dept_code.getText().toString();

                db.insert_department(name, code);

                showAllDepartments();

                btm_sheet_dia_et_dept_name.setText("");
                btm_sheet_dia_et_dept_code.setText("");

                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(bottomSheetDialogView);
    }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_admin_departments_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(AdminDepartmentsActivity.this,AdminHomeActivity.class)));
    }


    public void showAllDepartments() {

        ArrayList<Departments> departmentsArrayList = db.showDepartments();

        adapter = new departmentRecyclerViewAdapter(departmentsArrayList); // assign to adapter variable

        departmentRecyclerViewAdapter adapter = new departmentRecyclerViewAdapter(departmentsArrayList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        RecyclerView rv = findViewById(R.id.activity_admin_departments_recycelerview);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);



    }

    // Set up the search view


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        Log.d("TAG", "onCreateOptionsMenu called");


        getMenuInflater().inflate(R.menu.activity_admin_departments_sv_menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.activity_admin_departments_sv).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("Search", "Query text changed to: " + newText);

                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

}