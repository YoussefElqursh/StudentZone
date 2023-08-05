package com.studentzone.Doctor_Classes.Doctor_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


import com.studentzone.Data_Base.Courses;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.Doctor_Classes.Doctor_Models.DoctorCoursesRecyclerViewAdapter;
import com.studentzone.R;

import java.util.ArrayList;

public class DoctorCoursesActivity extends AppCompatActivity {


    // Database object
    private final My_DB db = new My_DB(this);
    private Button btn_back;
    private RecyclerView doctorCourseRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_subjects);


        setBackButtonAction();  // Go back to previous activity
        displayAllCourses();

    }

    /** setBackButtonAction()
     *  Back To The Previous Activity
     **********************************************************************************************/
    public void setBackButtonAction(){
        btn_back = findViewById(R.id.activity_doctor_subjects_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(DoctorCoursesActivity.this, DoctorHomeActivity.class)));
    }

    /**displayAllCourses()
     * Retrieve all courses from the database and display them in the RecyclerView
     **********************************************************************************************/
    public void displayAllCourses() {

        ArrayList<Courses> coursesList = db.displayDoctorCourses();

        doctorCourseRecyclerView = findViewById(R.id.doctor_subjects_recycleview);


        // Adapter for the course RecyclerView
        DoctorCoursesRecyclerViewAdapter adapter = new DoctorCoursesRecyclerViewAdapter(this,coursesList); // assign to adapter variable
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        doctorCourseRecyclerView.setHasFixedSize(true);
        doctorCourseRecyclerView.setLayoutManager(lm);
        doctorCourseRecyclerView.setAdapter(adapter);

    }

}