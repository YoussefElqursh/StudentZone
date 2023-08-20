package com.studentzone.Admin_Classes.Admin_Activities;

import android.annotation.SuppressLint;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Admin_Classes.Admin_Models.StudentRecyclerViewAdapter;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.Data_Base.Students;
import com.studentzone.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity for managing students in the admin panel.
 */

public class AdminStudentsAccountsActivity extends AppCompatActivity {

    // Database object
    private final My_DB db = new My_DB(this);

    // Views
    private Button btn_add_student, btn_back, btn_save_student, btn_close_add_student_dialog, btn_show_search, btn_hide_search;
    private EditText et_add_new_student_name, et_add_new_student_password, et_add_new_student_email, et_add_new_student_aid,et_add_new_student_phone, et_search;
    private RadioGroup rg_gender;
    private BottomSheetDialog addStudentBottomSheetDialog;
    private View addStudentBottomSheetDialogView;
    private SearchableSpinner departmentSpinner;

    // Variables for storing students data
    private String studentAID, studentName, studentEmail, studentPassword, studentGender = "Male",studentPhone,studentDepartmentName;
    private RecyclerView studentRecyclerView;
    private Toolbar toolbar;
    private LinearLayout ll_search;
    private StudentRecyclerViewAdapter adapter;
    private ArrayList<Students>filteredStudentList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_students_accounts);


        // Set the layout for the activity
        setContentView(R.layout.activity_admin_students_accounts);

        // Initialize views
        initializeViews();

        // Fill out the  spinner with data(department's names)
        fillOutSpinner();


        // Display all students in the RecyclerView
        displayAllStudents();

        // search for students
        setupSearchFunctionality();

        // Set button actions
        setAddStudentButtonAction(); // Add new student
        setBackButtonAction(); // Go back to previous activity
        setStudentGenderRadioGroupAction(); // Set gender of new student
        setSaveStudentButtonAction(); // Save new student data to database
        setCloseAddStudentDialogButtonAction(); // Close the "add student" dialog
        setButtonSearchAction(); // show search et
        setButtonBackSearchAction(); // hide search et
    }


    /** initializeViews()
     *  inflate
     **********************************************************************************************/
    @SuppressLint("WrongViewCast")
    public void initializeViews() {
        btn_add_student = findViewById(R.id.activity_admin_students_accounts_btn_add);
        btn_back = findViewById(R.id.activity_admin_students_accounts_btn_back);
        btn_show_search = findViewById(R.id.activity_admin_students_accounts_btn_search);
        btn_hide_search = findViewById(R.id.activity_admin_students_accounts_btn_search_back);

        addStudentBottomSheetDialog = new BottomSheetDialog(AdminStudentsAccountsActivity.this, R.style.BottomSheetStyle);
        addStudentBottomSheetDialogView = getLayoutInflater().inflate(R.layout.fragment_admin_add_student_account, null, false);

        btn_save_student = addStudentBottomSheetDialogView.findViewById(R.id.fragment_new_student_btn_save);
        btn_close_add_student_dialog = addStudentBottomSheetDialogView.findViewById(R.id.fragment_admin_new_student_btn_close);

        et_add_new_student_name = addStudentBottomSheetDialogView.findViewById(R.id.fragment_new_student_et_studet_name);
        et_add_new_student_aid = addStudentBottomSheetDialogView.findViewById(R.id.fragment_new_student_et_student_aid);
        et_add_new_student_password = addStudentBottomSheetDialogView.findViewById(R.id.fragment_new_student_et_student_password);
        et_add_new_student_email = addStudentBottomSheetDialogView.findViewById(R.id.fragment_new_student_et_student_email);
        et_add_new_student_phone = addStudentBottomSheetDialogView.findViewById(R.id.fragment_new_student_et_student_phone);
        et_search = findViewById(R.id.activity_admin_students_accounts_et_search);

        departmentSpinner = addStudentBottomSheetDialogView.findViewById(R.id.fragment_new_student_sp_department);

        toolbar = findViewById(R.id.activity_admin_students_accounts_tbar);
        ll_search = findViewById(R.id.activity_admin_students_accounts_ll_search);

        rg_gender = addStudentBottomSheetDialogView.findViewById(R.id.fragment_new_student_rg_student_kind);

        studentRecyclerView = findViewById(R.id.activity_admin_students_accounts_recyclerview);
    }


    /** setAddStudentButtonAction()
     *  show Bottom Sheet Dialog
     **********************************************************************************************/
    public void setAddStudentButtonAction() {
        btn_add_student.setOnClickListener(v -> {

            addStudentBottomSheetDialog.setContentView(addStudentBottomSheetDialogView);
            addStudentBottomSheetDialog.show();
        });
    }

    /** setCloseAddStudentDialogButtonAction()
     *  close Bottom Sheet Dialog
     **********************************************************************************************/
    public void setCloseAddStudentDialogButtonAction() {
        btn_close_add_student_dialog.setOnClickListener(v -> {
            addStudentBottomSheetDialog.dismiss();
            clearAddStudentDialogEditTextFields();
        });
    }

    /** setSaveStudentButtonAction()
     *  check Validation Of Entered Data And save It
     **********************************************************************************************/
    public void setSaveStudentButtonAction() {
        btn_save_student.setOnClickListener(v -> {

            studentName = et_add_new_student_name.getText().toString();
            studentEmail = et_add_new_student_email.getText().toString();
            studentPassword = et_add_new_student_password.getText().toString();
            studentAID = et_add_new_student_aid.getText().toString().trim();
            studentPhone = et_add_new_student_phone.getText().toString().trim();

            // Check for null values before calling toString()
            if (departmentSpinner.getSelectedItem() != null) {
                studentDepartmentName = departmentSpinner.getSelectedItem().toString();
            }


            if (TextUtils.isEmpty(studentName)) {
                et_add_new_student_name.setError("Is Required !");
                return;
            }
            if (TextUtils.isEmpty(studentAID)) {
                et_add_new_student_aid.setError("Is Required !");
                return;
            }

            if (TextUtils.isEmpty(studentEmail)) {
                et_add_new_student_email.setError("Is Required !");
                return;
            }
            if (!emailShouldEndsWithEdu(studentEmail)) {
                et_add_new_student_email.setError("Should End With .edu");
                return;
            }
            if (TextUtils.isEmpty(studentPassword)) {
                et_add_new_student_password.setError("Is Required !");
                return;
            }

            if (TextUtils.isEmpty(studentPhone) || !studentPhone.startsWith("01") || studentPhone.length()<11 || !android.util.Patterns.PHONE.matcher(studentPhone).matches()) {
                et_add_new_student_phone.setError("Please enter"+ "\n"+ "valid phone number!");
                return;
            }
            if (TextUtils.isEmpty(studentDepartmentName)|| studentDepartmentName.equals("Student Department")) {
                Toast.makeText(AdminStudentsAccountsActivity.this, "Please assign a student to department", Toast.LENGTH_SHORT).show();
                return;
            }

            saveNewStudentToDatabase();
            clearAddStudentDialogEditTextFields();
            displayAllStudents();
        });
    }

    /** setStudentGenderRadioGroupAction()
     *  to get gender of student
     ******************************************************************************************/
    public void setStudentGenderRadioGroupAction(){

        rg_gender.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == R.id.fragment_new_student_rb_male)
                studentGender = "Male";
            else if (checkedId == R.id.fragment_new_student_rb_female)
                studentGender = "Female";


        });
    }

    /** setBackButtonAction()
     *  Back To The Previous Activity
     **********************************************************************************************/
    public void setBackButtonAction() {
        btn_back = findViewById(R.id.activity_admin_students_accounts_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), AdminHomeActivity.class)));
    }

    /** saveNewStudentToDatabase()
     * add New Student
     ******************************************************************************************/
    public void saveNewStudentToDatabase() {

        studentAID = et_add_new_student_aid.getText().toString().trim();
        studentName = et_add_new_student_name.getText().toString().trim();
        studentEmail = et_add_new_student_email.getText().toString().trim();
        studentPassword = et_add_new_student_password.getText().toString().trim();
        studentPhone = et_add_new_student_phone.getText().toString().trim();
        int studentDepartmentID = db.getDepartmentIdByName( studentDepartmentName)
                ;

        Students student = new Students(studentAID, studentName, studentName, studentGender, studentEmail, studentPassword,studentPhone,studentDepartmentID);
        boolean added = db.addNewStudent(student);

        if(added){
            Toast.makeText(this, studentName + "Is Successfully Saved.", Toast.LENGTH_SHORT).show();
            addStudentBottomSheetDialog.dismiss();
        }
        displayAllStudents();
    }


    /**fillOutSpinner()
     * Fills out the department spinner with data(department's names) from the database.
     **********************************************************************************************/
    public void fillOutSpinner(){

        // Get all department's name from  database
        List<String> departments = db.getAllDepartmentsNames();

        // Create adapters for the spinner and set it to it's respective spinners
        departments.add(0, "Student Department");
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(AdminStudentsAccountsActivity.this, android.R.layout.simple_spinner_dropdown_item, departments);
        departmentSpinner.setAdapter(arrayAdapter1);
    }

    /** emailShouldEndsWithEdu()
     * Special Validation For Email To End With .edu
     **********************************************************************************************/
    public static boolean emailShouldEndsWithEdu(String input) {
        return !TextUtils.isEmpty(input) && input.endsWith(".edu");
    }

    /** clearAddStudentDialogEditTextFields()
     *  FillOut  EditTexts After Add New Student Or After Close Bottom Sheet Dialog()
     **********************************************************************************************/
    public void clearAddStudentDialogEditTextFields() {

        et_add_new_student_name.getText().clear();
        et_add_new_student_email.getText().clear();
        et_add_new_student_password.getText().clear();
        et_add_new_student_aid.getText().clear();
        et_add_new_student_phone.getText().clear();

        departmentSpinner.setSelection(0);
    }


    /**displayAllStudents()
     **********************************************************************************************/
    public void displayAllStudents() {

        ArrayList<Students> studentsArrayList = db.displayStudents("");

        filteredStudentList = new ArrayList<>(studentsArrayList);
        adapter = new StudentRecyclerViewAdapter(this, studentsArrayList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        studentRecyclerView.setHasFixedSize(true);
        studentRecyclerView.setLayoutManager(lm);
        studentRecyclerView.setAdapter(adapter);
    }

    /**setButtonSearchAction()
     * Make button search show search edit text and hide toolbar
     **********************************************************************************************/
    public void setButtonSearchAction() {

        btn_show_search.setOnClickListener(v -> {

            toolbar.setVisibility(View.INVISIBLE);
            ll_search.setVisibility(View.VISIBLE);

            et_search.setText("");

            //Show make a cursor focus on edit text when click on search button
            et_search.requestFocus(v.getTextDirection());

            //Show keyboard INPUT METHOD SERVICE when click on search button
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(et_search, InputMethodManager.SHOW_IMPLICIT);

            //Make animation when click on search btn
            Animation animation = AnimationUtils.loadAnimation(AdminStudentsAccountsActivity.this, R.anim.anim_activities_show_search);
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

            et_search.getText().clear();

            //Make animation when click on search back
            Animation animation = AnimationUtils.loadAnimation(AdminStudentsAccountsActivity.this, R.anim.anim_activities_hide_search);
            toolbar.startAnimation(animation);

            //Close Keyboard INPUT METHOD SERVICE when click on search button
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);

        });
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
                String searchKye =  s.toString().toLowerCase().trim();

                // Perform search operation as the user types in the edit text
                performSearch(searchKye);
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    /**performSearch()
     * This method get searched Students from data base and pss it to RecyclerView to update it
     **********************************************************************************************/
    private  void performSearch(String searchKye){

        filteredStudentList = db.displayStudents(searchKye);

        adapter.updateStudents(filteredStudentList);
    }

}