package com.studentzone.Doctor_Classes.Doctor_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;

import java.util.ArrayList;


public class DoctorSubjectsActivity extends AppCompatActivity { //main
    RecyclerView recyclerView;
    ArrayList<Model>arrayList;
    //store in the recycler


    Button btn_back;
    My_DB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_subjects);
        buttonBackAction();
       recyclerView=findViewById(R.id.recyclerview);//add item in recycler
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        My_DB my_db=new My_DB(getApplicationContext());
           arrayList=my_db.getCourses();//Method to get all courses name and courses code

            ModelRecyclerView_adapter modelRecyclerView=new ModelRecyclerView_adapter(this,arrayList);
            recyclerView.setAdapter(modelRecyclerView); //add model to recycler view


    }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_doctor_subjects_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(DoctorSubjectsActivity.this, DoctorHomeActivity.class)));
    }


}