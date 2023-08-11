package com.studentzone.Doctor_Classes.Doctor_Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.Data_Base.My_DB;
import com.studentzone.Data_Base.Students;
import com.studentzone.Doctor_Classes.Doctor_Models.DoctorStudentsGradesRecyclerViewAdapter;
import com.studentzone.R;

import java.util.ArrayList;

public class DoctorStudentGrades extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_student_assessment);

        setBackButtonAction();
        displayAllStudents();



    }

    public void setBackButtonAction(){
        Button btn_back = findViewById(R.id.activity_doctor_assessStudent_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(DoctorStudentGrades.this, DoctorAssessStudentActivity.class)));
    }

    /**displayAllStudents()
     **********************************************************************************************/
    public void displayAllStudents() {

        My_DB db = new My_DB(getBaseContext());

        String courseId = getIntent().getStringExtra("courseId");
        int courseID = Integer.parseInt(courseId);

        ArrayList<Students> studentsArrayList = db.getEnrolledStudentsByCourseId(courseID);
        RecyclerView studentRecyclerView = findViewById(R.id.activity_doctor_assessStudent_recycleView);

        DoctorStudentsGradesRecyclerViewAdapter adapter = new DoctorStudentsGradesRecyclerViewAdapter(studentsArrayList, this);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        studentRecyclerView.setHasFixedSize(true);
        studentRecyclerView.setLayoutManager(lm);
        studentRecyclerView.setAdapter(adapter);
    }
}
