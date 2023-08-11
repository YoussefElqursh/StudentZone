package com.studentzone.Doctor_Classes.Doctor_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.Data_Base.Courses;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.Doctor_Classes.Doctor_Models.DoctorAssessStudentActivityRecyclerIewAdapter;
import com.studentzone.R;

import java.util.ArrayList;

public class DoctorAssessStudentActivity extends AppCompatActivity {

    // Database object
    private final My_DB db = new My_DB(this);
    private Button btn_back;
    private RecyclerView doctorCourseAssessRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_students_assessment);

        buttonBackAction();
        displayAllCourses();
    }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_doctor_assessStudent_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(DoctorAssessStudentActivity.this, DoctorHomeActivity.class)));
    }

    /**displayAllCourses()
     * Retrieve all courses which this doctor teach it from the database and display them in the RecyclerView
     **********************************************************************************************/
    public void displayAllCourses() {

        ArrayList<Courses> coursesList = db.displayDoctorCourses();

        doctorCourseAssessRecyclerView = findViewById(R.id.activity_doctor_assessStudent_recycleView);


        // Adapter for the course RecyclerView
        DoctorAssessStudentActivityRecyclerIewAdapter adapter = new DoctorAssessStudentActivityRecyclerIewAdapter(coursesList); // assign to adapter variable

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        doctorCourseAssessRecyclerView.setHasFixedSize(true);
        doctorCourseAssessRecyclerView.setLayoutManager(lm);
        doctorCourseAssessRecyclerView.setAdapter(adapter);

    }
}
