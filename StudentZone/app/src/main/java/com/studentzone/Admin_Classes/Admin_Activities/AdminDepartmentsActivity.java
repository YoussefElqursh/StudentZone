package com.studentzone.Admin_Classes.Admin_Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Admin_Classes.Admin_Models.DepartmentRecyclerViewAdapter;
import com.studentzone.Data_Base.Departments;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;

import java.util.ArrayList;

/**
 * Activity for managing departments in the admin panel.
 */
public class AdminDepartmentsActivity extends AppCompatActivity  {

    // Database object
    private final My_DB db = new My_DB(this);


    // Views
    private Button btn_add_department, btn_back, btn_save_department, btn_close_add_department_dialog;
    private EditText et_add_new_department_name, et_add_new_department_code;
    private BottomSheetDialog addDepartmentBottomSheetDialog;
    private View addDepartmentBottomSheetDialogView;

    // Variables for storing department data
    private String departmentName, departmentCode;

    private RecyclerView departmentRecyclerView;


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

    /** initializeViews()
     *  inflate
     **********************************************************************************************/
    public void initializeViews() {
        btn_add_department = findViewById(R.id.activity_admin_departments_btn_add);
        btn_back = findViewById(R.id.activity_admin_departments_btn_back);

        addDepartmentBottomSheetDialog = new BottomSheetDialog(AdminDepartmentsActivity.this, R.style.BottomSheetStyle);
        addDepartmentBottomSheetDialogView = getLayoutInflater().inflate(R.layout.fragment_admin_add_department, null, false);

        btn_save_department = addDepartmentBottomSheetDialogView.findViewById(R.id.fragment_new_department_btn_save);
        btn_close_add_department_dialog = addDepartmentBottomSheetDialogView.findViewById(R.id.fragment_admin_new_department_btn_close);

        et_add_new_department_name = addDepartmentBottomSheetDialogView.findViewById(R.id.fragment_new_department_et_name);
        et_add_new_department_code = addDepartmentBottomSheetDialogView.findViewById(R.id.fragment_new_department_et_code);

        departmentRecyclerView = findViewById(R.id.activity_admin_departments_recycelerview);


    }

    /** setAddDepartmentButtonAction()
     *  show Bottom Sheet Dialog
     **********************************************************************************************/
    public void setAddDepartmentButtonAction() {
        btn_add_department.setOnClickListener(v -> {

            addDepartmentBottomSheetDialog.setContentView(addDepartmentBottomSheetDialogView);
            addDepartmentBottomSheetDialog.show();
        });
    }

    /** setCloseAddDepartmentDialogButtonAction()
     *  close Bottom Sheet Dialog
     **********************************************************************************************/
    public void setCloseAddDepartmentDialogButtonAction() {
        btn_close_add_department_dialog.setOnClickListener(v -> {
            addDepartmentBottomSheetDialog.dismiss();
            clearAddDepartmentsDialogEditTextFields();
            displayAllDepartments();
        });
    }


    /** setSaveDepartmentButtonAction()
     *  check Validation Of Entered Data And save It
     **********************************************************************************************/
    public void setSaveDepartmentButtonAction() {
        btn_save_department.setOnClickListener(v -> {

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
        });
    }

    /** setBackButtonAction()
     *  Back To The Previous Activity
     **********************************************************************************************/
    public void setBackButtonAction(){
        btn_back = findViewById(R.id.activity_admin_departments_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(getBaseContext(),AdminHomeActivity.class)));
    }


    /** saveNewDepartmentToDatabase()
     *  add New Department
     **********************************************************************************************/
    public void saveNewDepartmentToDatabase() {


        My_DB db = new My_DB(getBaseContext());

        departmentName = et_add_new_department_name.getText().toString().trim();
        departmentCode = et_add_new_department_code.getText().toString().trim();


        Departments department = new Departments(departmentName, departmentCode);
        boolean added = db.addNewDepartment(department);

        if(added){
            Toast.makeText(getBaseContext(), ""+department.getName()+" Department Successfully Added.", Toast.LENGTH_SHORT).show();
            addDepartmentBottomSheetDialog.dismiss();
        }

        displayAllDepartments();
    }

    /** clearAddDepartmentsDialogEditTextFields()
     *  FillOut  EditTexts After Add New Department Or After Close Bottom Sheet Dialog
     **********************************************************************************************/
    public void clearAddDepartmentsDialogEditTextFields() {

        et_add_new_department_name.setText("");
        et_add_new_department_code.setText("");
    }



    /**displayAllDepartments()
     **********************************************************************************************/

    public void displayAllDepartments() {

        ArrayList<Departments> departmentsArrayList = db.displayAllDepartments();

        // assign to adapter variable

        DepartmentRecyclerViewAdapter adapter = new DepartmentRecyclerViewAdapter(AdminDepartmentsActivity.this,departmentsArrayList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        departmentRecyclerView.setHasFixedSize(true);
        departmentRecyclerView.setLayoutManager(lm);
        departmentRecyclerView.setAdapter(adapter);

    }



}