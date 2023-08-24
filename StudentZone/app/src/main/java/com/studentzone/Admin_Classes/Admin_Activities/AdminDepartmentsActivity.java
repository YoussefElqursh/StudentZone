package com.studentzone.Admin_Classes.Admin_Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
    private Button btn_add_department, btn_back, btn_save_department, btn_close_add_department_dialog, btn_show_search, btn_hide_search, btn_clear_searchKey;
    private EditText et_add_new_department_name, et_add_new_department_code;
    private BottomSheetDialog addDepartmentBottomSheetDialog;
    private View addDepartmentBottomSheetDialogView;


    // Variables for storing department data
    private String departmentName, departmentCode;
    private RecyclerView departmentRecyclerView;
    private Toolbar toolbar;
    private LinearLayout ll_search, ll_no_search_results;
    private EditText et_search;
    private  ArrayList<Departments> filteredDepartmentsList;
    private DepartmentRecyclerViewAdapter adapter;
    private int search_not_results_counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_departments);

        // Initialize views
        initializeViews();

        // Show all departments in the RecyclerView
        displayAllDepartments();

        // search for departments
        setupSearchFunctionality();

        // Set up button actions
        setAddDepartmentButtonAction();  // Add new department
        setSaveDepartmentButtonAction();  // Save new department data to database
        setCloseAddDepartmentDialogButtonAction();  // Close the "add department" dialog
        setBackButtonAction();  // Go back to previous activity
        setButtonSearchAction(); // show search et
        setButtonBackSearchAction(); // hide search et
        clearSearchKey(); //clear search edit text
    }

    /** initializeViews()
     *  inflate
     **********************************************************************************************/
    public void initializeViews() {
        btn_add_department = findViewById(R.id.activity_admin_departments_btn_add);
        btn_back = findViewById(R.id.activity_admin_departments_btn_back);
        btn_show_search = findViewById(R.id.activity_admin_departments_btn_search);
        btn_hide_search = findViewById(R.id.activity_admin_departments_btn_search_back);
        btn_clear_searchKey = findViewById(R.id.activity_admin_departments_btn_search_delete);

        addDepartmentBottomSheetDialog = new BottomSheetDialog(AdminDepartmentsActivity.this, R.style.BottomSheetStyle);
        addDepartmentBottomSheetDialogView = getLayoutInflater().inflate(R.layout.fragment_admin_add_department, null, false);

        btn_save_department = addDepartmentBottomSheetDialogView.findViewById(R.id.fragment_new_department_btn_save);
        btn_close_add_department_dialog = addDepartmentBottomSheetDialogView.findViewById(R.id.fragment_admin_new_department_btn_close);

        et_add_new_department_name = addDepartmentBottomSheetDialogView.findViewById(R.id.fragment_new_department_et_name);
        et_add_new_department_code = addDepartmentBottomSheetDialogView.findViewById(R.id.fragment_new_department_et_code);

        departmentRecyclerView = findViewById(R.id.activity_admin_departments_recyclerview);

        toolbar = findViewById(R.id.activity_admin_departments_tbar);

        ll_search = findViewById(R.id.activity_admin_departments_ll_search);
        ll_no_search_results = findViewById(R.id.activity_admin_departments_ll_no_search_results);

        et_search = findViewById(R.id.activity_admin_departments_et_search);
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

        ArrayList<Departments> departmentsArrayList = db.displayDepartments("");


        // Initialize the filtered departments list with all departments
         filteredDepartmentsList = new ArrayList<>(departmentsArrayList);

        // assign to adapter variable
        adapter = new DepartmentRecyclerViewAdapter(AdminDepartmentsActivity.this,departmentsArrayList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        departmentRecyclerView.setHasFixedSize(true);
        departmentRecyclerView.setLayoutManager(lm);
        departmentRecyclerView.setAdapter(adapter);

    }

    /**setButtonSearchAction()
     * Make button search show search edit text and hide toolbar
     **********************************************************************************************/
    public void setButtonSearchAction() {

        btn_show_search.setOnClickListener(v -> {

            toolbar.setVisibility(View.INVISIBLE);
            ll_search.setVisibility(View.VISIBLE);
            btn_add_department.setVisibility(View.INVISIBLE);


            //Show make a cursor focus on edit text when click on search button
            et_search.requestFocus(v.getTextDirection());

            //Show keyboard INPUT METHOD SERVICE when click on search button
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(et_search, InputMethodManager.SHOW_IMPLICIT);

            //Make animation when click on search btn
            Animation animation = AnimationUtils.loadAnimation(AdminDepartmentsActivity.this, R.anim.anim_activities_show_search);
            ll_search.startAnimation(animation);

        });
    }

    /**setButtonBackSearchAction()
     * Make button back Search hide search edit text and show toolbar
     **********************************************************************************************/
    public void setButtonBackSearchAction() {
        btn_hide_search.setOnClickListener(v -> {

            toolbar.setVisibility(View.VISIBLE);
            ll_search.setVisibility(View.INVISIBLE);
            btn_add_department.setVisibility(View.VISIBLE);

            et_search.getText().clear();

            //Make animation when click on search back
            Animation animation = AnimationUtils.loadAnimation(AdminDepartmentsActivity.this, R.anim.anim_activities_hide_search);
            toolbar.startAnimation(animation);

            //Close Keyboard INPUT METHOD SERVICE when click on search button
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);

        });
    }

    /**clearSearchKey()
     * this button appear in search edite text when user start in write and ,
     * clear the text in edite text when the user click on it
     **********************************************************************************************/
    private void clearSearchKey(){
        btn_clear_searchKey.setOnClickListener(v -> et_search.getText().clear());
    }

    /**setupSearchFunctionality()
     * Sets up the search functionality for the EditText view.
     * Adds a TextWatcher to monitor changes in the text as the user types.
     **********************************************************************************************/
    public void setupSearchFunctionality(){
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // Convert the text to lowercase and remove leading/trailing whitespace
                String searchKey =  s.toString().toLowerCase();

                // Perform search operation as the user types in the edit text
                if(searchKey.isEmpty() || searchKey.trim().isEmpty())
                    displayAllDepartments();
                else
                    performSearch(searchKey.trim());

                if(searchKey.isEmpty())
                    btn_clear_searchKey.setVisibility(View.INVISIBLE);
                else
                    btn_clear_searchKey.setVisibility(View.VISIBLE);

                handleSearchQueryResult();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    /**performSearch()
     * This method get searched doctors from data base and pss it to RecyclerView to update it
     **********************************************************************************************/
    private  void performSearch(String searchKye){

        filteredDepartmentsList = db.displayDepartments(searchKye);

        adapter.updateDepartments(filteredDepartmentsList);


    }

    /**handleSearchQueryResult()
     * This method is likely called after performing a search
     * It ensures that the appropriate views are shown or hidden based on whether there are search results available
     **********************************************************************************************/
    public void handleSearchQueryResult(){
        if(filteredDepartmentsList.size()>0){
            departmentRecyclerView.setVisibility(View.VISIBLE);
            ll_no_search_results.setVisibility(View.INVISIBLE);
            search_not_results_counter = 0;
        }
        else {
            ll_no_search_results.setVisibility(View.VISIBLE);
            departmentRecyclerView.setVisibility(View.INVISIBLE);

            if(search_not_results_counter == 0){
                //Make animation of no search results layout
                Animation animation = AnimationUtils.loadAnimation(AdminDepartmentsActivity.this, R.anim.anim_show_ll_no_search_results);
                ll_no_search_results.startAnimation(animation);
            }
            search_not_results_counter++;
        }
    }

}