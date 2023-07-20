package com.studentzone.Admin_Classes.Admin_Activities;
import android.annotation.SuppressLint;

import android.text.TextUtils;
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
    private My_DB db = new My_DB(this);
    private BottomSheetDialog addDepartmentBottomSheetDialog;
    private View addDepartmentBottomSheetDialogView;
    private RecyclerView departmentRecyclerView;
    private EditText et_add_new_department_name, et_add_new_department_code;
    private Button btn_add_department, btn_back, btn_save_department, btn_close_add_department_dialog;
    private departmentRecyclerViewAdapter adapter;
    private String departmentName, departmentCode;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_departments);


        // Initialize views
        initializeViews();

        // Show all departments in the RecyclerView
        displayAllDepartments();

        // Set up button actions
        setAddDepartmentButtonAction();  // Add new department
        setSaveDepartmentButtonAction();  // Save new department data to database
        setCloseAddDepartmentDialogButtonAction();  // Close the "add department" dialog
        setBackButtonAction();  // Go back to previous activity
    }

    /**inflate
     **********************************************************************************************/
    public void initializeViews() {
        btn_add_department = findViewById(R.id.activity_admin_departments_btn_add);
        btn_back = findViewById(R.id.activity_admin_departments_btn_back);

        addDepartmentBottomSheetDialog = new BottomSheetDialog(AdminDepartmentsActivity.this, R.style.BottomSheetStyle);
        addDepartmentBottomSheetDialogView = getLayoutInflater().inflate(R.layout.fragment_admin_new_department, null, false);

        btn_save_department = addDepartmentBottomSheetDialogView.findViewById(R.id.fragment_new_department_btn_save);
        btn_close_add_department_dialog = addDepartmentBottomSheetDialogView.findViewById(R.id.fragment_admin_new_department_btn_close);

        et_add_new_department_name = addDepartmentBottomSheetDialogView.findViewById(R.id.fragment_new_department_et_name);
        et_add_new_department_code = addDepartmentBottomSheetDialogView.findViewById(R.id.fragment_new_department_et_code);

        departmentRecyclerView = findViewById(R.id.activity_admin_departments_recycelerview);


    }

    /**show Bottom Sheet Dialog()
     **********************************************************************************************/
    public void setAddDepartmentButtonAction() {
        btn_add_department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addDepartmentBottomSheetDialog.setContentView(addDepartmentBottomSheetDialogView);
                addDepartmentBottomSheetDialog.show();
            }
        });
    }

    /** close Bottom Sheet Dialog()
     **********************************************************************************************/
    public void setCloseAddDepartmentDialogButtonAction() {
        btn_close_add_department_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDepartmentBottomSheetDialog.dismiss();
                clearAddDepartmentsDialogEditTextFields();
                displayAllDepartments();
            }
        });
    }


    /**check Validation Of Entered Data And save It()
     **********************************************************************************************/
    public void setSaveDepartmentButtonAction() {
        btn_save_department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                departmentName = et_add_new_department_name.getText().toString().trim();
                departmentCode = et_add_new_department_code.getText().toString().trim();


                if (TextUtils.isEmpty(departmentName)) {
                    et_add_new_department_name.setError("Is Required !");
                    return;
                }
                if (TextUtils.isEmpty(departmentCode)) {
                    et_add_new_department_code.setError("Is Required !");
                    return;
                }

                saveNewDepartmentToDatabase();
                clearAddDepartmentsDialogEditTextFields();
                displayAllDepartments();
            }
        });
    }

    /**Back To The Previous Activity()
     **********************************************************************************************/
    public void setBackButtonAction(){
        btn_back = findViewById(R.id.activity_admin_departments_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(getBaseContext(),AdminHomeActivity.class)));
    }


    /**add New Department
     **********************************************************************************************/
    public void saveNewDepartmentToDatabase() {


        My_DB db = new My_DB(getBaseContext());

        departmentName = et_add_new_department_name.getText().toString().trim();
        departmentCode = et_add_new_department_code.getText().toString().trim();


        Departments department = new Departments(departmentName, departmentCode);
        boolean added = db.addNewDepartment(department);

        if(added){
            Toast.makeText(getBaseContext(), ""+department.getName().toString()+" Department Successfully Added  ✔️", Toast.LENGTH_SHORT).show();
            addDepartmentBottomSheetDialog.dismiss();
        }

        displayAllDepartments();
    }

    /**FillOut  EditTexts After Add New Department Or After Close Bottom Sheet Dialog()
     **********************************************************************************************/
    public void clearAddDepartmentsDialogEditTextFields() {

        et_add_new_department_name.setText("");
        et_add_new_department_code.setText("");
    }



    /**Show All Departments
     **********************************************************************************************/

    public void displayAllDepartments() {

        ArrayList<Departments> departmentsArrayList = db.showDepartments();

        adapter = new departmentRecyclerViewAdapter(AdminDepartmentsActivity.this,departmentsArrayList); // assign to adapter variable

        departmentRecyclerViewAdapter adapter = new departmentRecyclerViewAdapter(AdminDepartmentsActivity.this,departmentsArrayList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        departmentRecyclerView.setHasFixedSize(true);
        departmentRecyclerView.setLayoutManager(lm);
        departmentRecyclerView.setAdapter(adapter);

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