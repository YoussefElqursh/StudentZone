package com.studentzone.Doctor_Classes.Doctor_Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.Admin_Classes.Admin_Activities.AdminDoctorsAccountsActivity;
import com.studentzone.Admin_Classes.Admin_Models.StudentRecyclerViewAdapter;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.Data_Base.Students;
import com.studentzone.Doctor_Classes.Doctor_Models.DoctorStudentsGradesRecyclerViewAdapter;
import com.studentzone.R;

import java.util.ArrayList;

public class DoctorStudentGrades extends AppCompatActivity {

    // Database object
    private final My_DB db = new My_DB(this);
    private Button btn_show_search, btn_hide_search;
    private EditText et_search;
    private Toolbar toolbar;
    private LinearLayout ll_search;
    private DoctorStudentsGradesRecyclerViewAdapter adapter;
    private ArrayList<Students> studentsList, filteredStudentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_students_grades);

        // Initialize views
        initializeViews();

        // Display all students in the RecyclerView
        displayAllStudents();

        // search for students
        setupSearchFunctionality();

        // Go back to previous activity
        setBackButtonAction();


        setButtonSearchAction(); // show search et
        setButtonBackSearchAction(); // hide search et

    }

    /** initializeViews()
     *  inflate
     **********************************************************************************************/
    public void initializeViews() {
        btn_show_search = findViewById(R.id.activity_doctor_student_degree_btn_search);
        btn_hide_search = findViewById(R.id.activity_doctor_student_degree_btn_search_back);

        et_search = findViewById(R.id.activity_doctor_student_degree_et_search);

        toolbar = findViewById(R.id.activity_doctor_student_degree_tbar);
        ll_search = findViewById(R.id.activity_doctor_student_degree_ll_search);
    }
    public void setBackButtonAction(){
        Button btn_back = findViewById(R.id.activity_doctor_student_degree_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(DoctorStudentGrades.this, DoctorAssessStudentActivity.class)));
    }

    /**displayAllStudents()
     **********************************************************************************************/
    public void displayAllStudents() {

        String courseId = getIntent().getStringExtra("courseId");
        int courseID = Integer.parseInt(courseId);

        studentsList = db.getEnrolledStudentsByCourseId(courseID);
        filteredStudentList = new ArrayList<>(studentsList);

        RecyclerView studentRecyclerView = findViewById(R.id.activity_doctor_student_degree_recycleView);

         adapter = new DoctorStudentsGradesRecyclerViewAdapter(studentsList, this);
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

            //Show make a cursor focus on edit text when click on search button
            et_search.requestFocus(v.getTextDirection());

            //Show keyboard INPUT METHOD SERVICE when click on search button
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(et_search, InputMethodManager.SHOW_IMPLICIT);

            //Make animation when click on search btn
            Animation animation = AnimationUtils.loadAnimation(DoctorStudentGrades.this, R.anim.anim_activities_show_search);
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

            et_search.setText("");
            //Make animation when click on search back
            Animation animation = AnimationUtils.loadAnimation(DoctorStudentGrades.this, R.anim.anim_activities_hide_search);
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
     his method filters the studentsList based on the search key and updates the RecyclerView adapter.
     **********************************************************************************************/
    private void performSearch(String searchKey) {
        filteredStudentList.clear();

        if (searchKey.isEmpty()) {
            filteredStudentList.addAll(studentsList);
        } else {
            for (Students student : studentsList) {
                if (student.getFName().toLowerCase().contains(searchKey)
                        || student.getAcademic_Number().toLowerCase().contains(searchKey)) {
                    filteredStudentList.add(student);
                }
            }
        }

        adapter.updateStudents(filteredStudentList);
    }
}