package com.studentzone.Student_Classes.Student_Activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Models.RegestrationModel.SubjectRegestrationAdapter;
import com.studentzone.Student_Classes.Student_Models.RegestrationModel.SubjectRegestrationModel;

import java.util.ArrayList;

public class StudentRegistrationActivity extends AppCompatActivity  {

    Button btn_back;

    RecyclerView recyclerView;
    ArrayList<SubjectRegestrationModel>arrayList;
    //store in the recycler
    Button btn_After_Registration;

    My_DB my_db=new My_DB( this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        btn_After_Registration=  findViewById(R.id.fragment_new_department_btn_save);
        buttonBackAction();
        recyclerView=findViewById(R.id.recycleview_student_regestration_choose_subject );//add item in recycler
        if(my_db.getSumOfSubjectHours_as_all()>=63){
            if(my_db.studentCs()==2){
                arrayList=my_db.getCourses_for_students_level3();
                arrayList.addAll(my_db.getCourses_for_students_level2());
            }
        } else if (my_db.getSumOfSubjectHours_as_all()>27) {
            arrayList=my_db.getCourses_for_students_level2();
            arrayList.addAll(my_db.getCourses_for_students_level1());
        } else  {
            arrayList=my_db.getCourses_for_students_level1();//Method to get all courses name and courses code


        }
        SubjectRegestrationAdapter subjectRegestrationAdapter=new SubjectRegestrationAdapter(this,arrayList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(subjectRegestrationAdapter); //add model to recycler view


        btn_After_Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(subjectRegestrationAdapter.getCourse_id()!=null&&!subjectRegestrationAdapter.getCourse_id().isEmpty())
                {
                    ArrayList<Integer> CourseId=subjectRegestrationAdapter.getCourse_id();
                    for (Integer id : CourseId) {
                        my_db.insertEnrollmentTable(id);
                    }
                    startActivity(new Intent(StudentRegistrationActivity.this,StudentSubjectActivity.class));
                    recreate();
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(StudentRegistrationActivity.this);
                    builder.setTitle("Watch Out!");
                    builder.setMessage("you have to regist atleast one subject .");
                    builder.setNegativeButton("Ok", null);
                    builder.show();
                }







            }
        });







    }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_student_regestration_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(StudentRegistrationActivity.this, StudentHomeActivity.class)));
    }



}