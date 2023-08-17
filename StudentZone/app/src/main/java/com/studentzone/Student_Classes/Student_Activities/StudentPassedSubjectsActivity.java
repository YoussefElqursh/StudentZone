package com.studentzone.Student_Classes.Student_Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Models.RegestrationModel.SubjectRegestrationAdapter;
import com.studentzone.Student_Classes.Student_Models.RegestrationModel.SubjectRegestrationModel;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.StudentPassedModel;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.StudentPassedSubjectsAdapter;

import java.util.ArrayList;
public class StudentPassedSubjectsActivity extends AppCompatActivity {

    Button btn_back;

    RecyclerView recyclerView;
    ArrayList<StudentPassedModel> arrayList;
    //store in the recycler
    Button btn_After_Registration;
    SubjectRegestrationAdapter subjectRegestrationAdapter;
    My_DB my_db=new My_DB( this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_passed_subjects);
        buttonBackAction();

        recyclerView=findViewById(R.id.student_passed_subjects_recycleview );//add item in recycler
        arrayList=my_db.getPassedCoursesForStudents();//Method to get all courses name and courses code
        StudentPassedSubjectsAdapter studentPassedSubjectsAdapter=new StudentPassedSubjectsAdapter(this,arrayList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(studentPassedSubjectsAdapter); //add model to recycler view

    }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_student_passed_subjects_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(StudentPassedSubjectsActivity.this, StudentHomeActivity.class)));
    }




}