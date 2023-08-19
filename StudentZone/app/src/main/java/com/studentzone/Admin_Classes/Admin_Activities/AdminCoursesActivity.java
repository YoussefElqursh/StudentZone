package com.studentzone.Admin_Classes.Admin_Activities;

import android.content.Context;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import com.studentzone.Admin_Classes.Admin_Models.CourseRecyclerViewAdapter;
import com.studentzone.Data_Base.Courses;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity for managing courses in the admin panel.
 */
public class AdminCoursesActivity extends AppCompatActivity {

    // Database object
    private final My_DB db = new My_DB(this);

    // Views
    private Button btn_add_course, btn_back, btn_save_course, btn_close_add_course_dialog, btn_show_search, btn_hide_search;
    private EditText et_add_new_course_name, et_add_new_course_code, et_add_new_course_numberOfHours, et_search;
    private RecyclerView courseRecyclerView;
    private View addCourseBottomSheetDialogView;
    private BottomSheetDialog addCourseBottomSheetDialog;
    private SearchableSpinner departmentSpinner, doctorSpinner, preRequestSpinner;
    private  Spinner levelSpinner;

    // Variables for storing course data
    private String courseName, courseCode , courseDepartmentName , courseDoctorName , preRequestCourseName = "None",courseLevel;

    private  int courseNumberOfHours;
    private ArrayAdapter<String> preRequestSpinnerAdapter;

    private Toolbar toolbar;
    private LinearLayout ll_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_subjects);


        // Initialize views
        initializeViews();


        // Show all Courses in the RecyclerView
        displayAllCourses();

        // Fill out the three spinners with data
        fillOutThreeSpinners();

        // Set up button actions
        setAddCourseButtonAction();// Add new Course
        setSaveCourseButtonAction();  // Save new Course data to database
        setCloseAddCourseDialogButtonAction();  // Close the "add Course" dialog
        setBackButtonAction();  // Go back to previous activity
        setButtonSearchAction(); // show search et
        setButtonBackSearchAction(); // hide search et
    }


    /**
     * initializeViews()
     *  Initializes all views used in the activity.
     **********************************************************************************************/
    public void initializeViews() {
        btn_add_course = findViewById(R.id.activity_admin_subjects_btn_add);
        btn_back = findViewById(R.id.activity_admin_subjects_btn_back);
        btn_show_search = findViewById(R.id.activity_admin_subjects_btn_search);
        btn_hide_search = findViewById(R.id.activity_admin_subjects_btn_search_back);

        addCourseBottomSheetDialog = new BottomSheetDialog(AdminCoursesActivity.this, R.style.BottomSheetStyle);
        addCourseBottomSheetDialogView = getLayoutInflater().inflate(R.layout.fragment_admin_add_subject, null, false);

        btn_save_course = addCourseBottomSheetDialogView.findViewById(R.id.fragment_new_subject_btn_save);
        btn_close_add_course_dialog = addCourseBottomSheetDialogView.findViewById(R.id.fragment_admin_new_subject_btn_close);

        et_add_new_course_name = addCourseBottomSheetDialogView.findViewById(R.id.fragment_new_subject_et_name);
        et_add_new_course_code = addCourseBottomSheetDialogView.findViewById(R.id.fragment_new_subject_et_code);
        et_search = findViewById(R.id.activity_admin_subjects_et_search);

        et_add_new_course_numberOfHours = addCourseBottomSheetDialogView.findViewById(R.id.fragment_new_subject_et_subject_hours);

        departmentSpinner = addCourseBottomSheetDialogView.findViewById(R.id.fragment_new_subject_sp_department);
        doctorSpinner = addCourseBottomSheetDialogView.findViewById(R.id.fragment_new_subject_sp_doctor_name);
        preRequestSpinner = addCourseBottomSheetDialogView.findViewById(R.id.fragment_new_subject_sp_subject_pre_request);
        levelSpinner = addCourseBottomSheetDialogView.findViewById(R.id.fragment_new_subject_sp_subject_level);

        toolbar = findViewById(R.id.activity_admin_subjects_tbar);
        ll_search = findViewById(R.id.activity_admin_subjects_ll_search);

        courseRecyclerView = findViewById(R.id.activity_admin_subjects_recycleView);
    }


    /**
     * setAddSubjectButtonAction()
     * Sets up the "Add Course" button's onClick listener to show the "Add Course" dialog.
     **********************************************************************************************/
    public void setAddCourseButtonAction() {
        btn_add_course.setOnClickListener(v -> {
            addCourseBottomSheetDialog.setContentView(addCourseBottomSheetDialogView);

            // Update the spinners with the latest data from the database
            fillOutThreeSpinners();

            addCourseBottomSheetDialog.show();
        });
    }


    /** setCloseAddSubjectDialogButtonAction()
     *  Closes the "Add Course" dialog when the close button is clicked.
     **********************************************************************************************/
    public void setCloseAddCourseDialogButtonAction() {
        btn_close_add_course_dialog.setOnClickListener(v -> {
            addCourseBottomSheetDialog.dismiss();
            clearAddCoursesDialogEditTextFieldsAndSpinners();
            displayAllCourses();

        });
    }


    /** setSaveCourseButtonAction()
     *  Validates the entered course data and saves it to the database when the "Save" button is clicked.
     **********************************************************************************************/
    public void setSaveCourseButtonAction() {
        btn_save_course.setOnClickListener(v -> {

            // Get course data from the EditTexts and spinners

            courseName = et_add_new_course_name.getText().toString().trim();
            courseCode = et_add_new_course_code.getText().toString().trim();

            String courseNumberOfHoursString = et_add_new_course_numberOfHours.getText().toString().trim();
            if (!TextUtils.isEmpty(courseNumberOfHoursString) )
                courseNumberOfHours = Integer.parseInt(courseNumberOfHoursString);



            // Check for null values before calling toString()
            if (departmentSpinner.getSelectedItem() != null) {
                courseDepartmentName = departmentSpinner.getSelectedItem().toString();
            }

            if (doctorSpinner.getSelectedItem() != null) {
                courseDoctorName = doctorSpinner.getSelectedItem().toString();
            }

            if (preRequestSpinner.getSelectedItem() != null) {
                preRequestCourseName = preRequestSpinner.getSelectedItem().toString();
            }
            if (levelSpinner.getSelectedItem() != null) {
                courseLevel =  levelSpinner.getSelectedItem().toString();
            }


            // Validate the entered course data
            if (TextUtils.isEmpty(courseName)) {
                et_add_new_course_name.setError("This field is required!");
                return;
            }
            if (TextUtils.isEmpty(courseCode)) {
                et_add_new_course_code.setError("This field is required!");
                return;
            }
            if (TextUtils.isEmpty(et_add_new_course_numberOfHours.getText())) {
                et_add_new_course_numberOfHours.setError("This field is required!");
                return;
            }
            if (TextUtils.isEmpty(courseLevel) || courseLevel.equals("Course Level")) {
                Toast.makeText(AdminCoursesActivity.this, "Please assign a level to this course", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(et_add_new_course_numberOfHours.getText()) || courseNumberOfHours<0 || courseNumberOfHours>6) {
                et_add_new_course_numberOfHours.setError("Please, Enter Valid Number Of Hours! (0-6)");
                return;
            }
            if (TextUtils.isEmpty(courseDepartmentName)|| courseDepartmentName.equals("Course Department")) {
                Toast.makeText(AdminCoursesActivity.this, "Please select a department", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(courseDoctorName) || courseDoctorName.equals("Doctor Name")) {
                Toast.makeText(AdminCoursesActivity.this, "Please assign a doctor to this course", Toast.LENGTH_SHORT).show();
                return;
            }


            // Save the new course data to the database
            saveNewCourseToDatabase();

            // Clear the "Add Course" dialog's EditText fields and display all courses in the RecyclerView
            clearAddCoursesDialogEditTextFieldsAndSpinners();
            displayAllCourses();
        });
    }

    /** setBackButtonAction()
     *  Back To The Previous Activity
     **********************************************************************************************/
    public void setBackButtonAction(){
        btn_back = findViewById(R.id.activity_admin_subjects_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(getBaseContext(),AdminHomeActivity.class)));
    }


    /** saveNewCourseToDatabase()
     *  Adds a new course to the database.
     **********************************************************************************************/
    public void saveNewCourseToDatabase() {


        My_DB db = new My_DB(getBaseContext());

        // Get the IDs of the selected department, doctor, and prerequisite course
        int departmentId = db.getDepartmentIdByName(courseDepartmentName);
        int doctorId = db.getDoctorIdByName(courseDoctorName);
        int preRequestId = db.getPreRequestIdByName(preRequestCourseName);

        // Create a new course object with the entered data and insert it into the database
        Courses course = new Courses(courseName, courseCode, departmentId, doctorId, preRequestId,Integer.parseInt(courseLevel),courseNumberOfHours);

        boolean added = db.addNewCourse(course);

        if(added){
            Toast.makeText(getBaseContext(), ""+course.getName()+" Course Successfully Added.", Toast.LENGTH_SHORT).show();
            addCourseBottomSheetDialog.dismiss();
        }

        displayAllCourses();
    }


    /**fillOutThreeSpinners()
     * Fills out the department, doctor, and prerequisite course spinners with data from the database.
     **********************************************************************************************/
    public void fillOutThreeSpinners(){

        // Get all departments, doctors, and courses from the database
        List<String> departments = db.getAllDepartmentsNames();
        List<String> doctors = db.getAllDoctorsNames();
        List<String> courses = db.getAllCoursesNames();
        List<String> levels = new ArrayList<>(5);


        // Create adapters for the spinners and set them to their respective spinners

        // Set up the courses levels Spinner
        levels.add(0,"Course Level");
        levels.add(1,"1");
        levels.add(2,"2");
        levels.add(3,"3");
        levels.add(4,"4");

        ArrayAdapter<String> arrayAdapter0 = new ArrayAdapter<>(AdminCoursesActivity.this, android.R.layout.simple_spinner_dropdown_item, levels);
        levelSpinner.setAdapter(arrayAdapter0);
        levelSpinner.setSelection(0);

        // Set up the courses departments Spinner
        departments.add(0, "Course Department");//Doctor Name
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(AdminCoursesActivity.this, android.R.layout.simple_spinner_dropdown_item, departments);
        departmentSpinner.setAdapter(arrayAdapter1);
//      departmentSpinner.setSelection(0);

        // Set up the courses doctor Spinner
        doctors.add(0, "Doctor Name");
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(AdminCoursesActivity.this, android.R.layout.simple_spinner_dropdown_item, doctors);
        doctorSpinner.setAdapter(arrayAdapter2);
        doctorSpinner.setSelection(0);

        // Set up the pre-request course spinner
        courses.add(0, "Subject Previous Request");
        preRequestSpinnerAdapter = new ArrayAdapter<>(AdminCoursesActivity.this, android.R.layout.simple_spinner_dropdown_item, courses);
        preRequestSpinner.setAdapter(preRequestSpinnerAdapter);
        preRequestSpinner.setSelection(0);
    }

    /** clearAddCoursesDialogEditTextFields()
     *  FillOut  EditTexts After Add New Subject Or After Close Bottom Sheet Dialog
     **********************************************************************************************/
    public void clearAddCoursesDialogEditTextFieldsAndSpinners() {

        et_add_new_course_name.getText().clear();
        et_add_new_course_code.getText().clear();
        et_add_new_course_numberOfHours.getText().clear();

        departmentSpinner.setSelection(0);
        doctorSpinner.setSelection(0);
        preRequestSpinner.setSelection(0);
        levelSpinner.setSelection(0);

        preRequestSpinnerAdapter.clear();
        fillOutThreeSpinners();
    }

    /**displayAllCourses()
     * Retrieve all courses from the database and display them in the RecyclerView
     **********************************************************************************************/
    public void displayAllCourses() {

        ArrayList<Courses> coursesList = db.displayAllCourses();

        // Adapter for the course RecyclerView
        CourseRecyclerViewAdapter adapter = new CourseRecyclerViewAdapter(this,coursesList); // assign to adapter variable
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        courseRecyclerView.setHasFixedSize(true);
        courseRecyclerView.setLayoutManager(lm);
        courseRecyclerView.setAdapter(adapter);

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
            Animation animation = AnimationUtils.loadAnimation(AdminCoursesActivity.this, R.anim.anim_activities_show_search);
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
            Animation animation = AnimationUtils.loadAnimation(AdminCoursesActivity.this, R.anim.anim_activities_hide_search);
            toolbar.startAnimation(animation);

            //Close Keyboard INPUT METHOD SERVICE when click on search button
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);
        });

    }

}