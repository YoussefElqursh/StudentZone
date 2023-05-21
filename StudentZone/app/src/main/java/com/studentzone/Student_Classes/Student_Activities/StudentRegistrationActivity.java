package com.studentzone.Student_Classes.Student_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.studentzone.Admin_Calsses.Admin_Activities.AdminDepartmentsActivity;
import com.studentzone.Admin_Calsses.Admin_Activities.AdminHomeActivity;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Models.RegestrationModel.SubjectRegestrationAdapter;
import com.studentzone.Student_Classes.Student_Models.RegestrationModel.SubjectRegestrationModel;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.SubjectAdapter;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.SubjectModel;

import java.util.ArrayList;
import java.util.List;

public class StudentRegistrationActivity extends AppCompatActivity {

    Button btn_back;

   // CheckBox resg_checkbox=findViewById(R.id.activity_student_subject_tv_sub_state);
   // Button register=findViewById(R.id.activity_student_regestration_btn_back);
    My_DB my_db=new My_DB( this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        buttonBackAction();

        SubjectRegestedRecyclerView();
        ArrayList<String> name_course = my_db.Get_all_courses_for_student();
        SubjectRegestrationRecyclerView(name_course);
      //  register.setOnClickListener(v ->  my_db.regestration_student(resg_checkbox) );


    }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_student_regestration_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(StudentRegistrationActivity.this, StudentHomeActivity.class)));
    }

    public RecyclerView SubjectRegestrationRecyclerView(ArrayList<String> v)
    {


        RecyclerView recyclerView = findViewById(R.id.recycleview_student_regestration_choose_subject);

        List<SubjectRegestrationModel>subjectRegestrationModel=new ArrayList<SubjectRegestrationModel>();

        for (int i = 0; i <v.size() ; i++) {


            subjectRegestrationModel.add(new SubjectRegestrationModel(v.get(i)));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SubjectRegestrationAdapter(getApplicationContext(),subjectRegestrationModel));
        return recyclerView;
    }

    public RecyclerView SubjectRegestedRecyclerView()
    {
        RecyclerView recyclerView = findViewById(R.id.recycleview_student_regestration_choosed_subject);

        List<SubjectRegestrationModel>subjectRegestrationModel=new ArrayList<SubjectRegestrationModel>();
        subjectRegestrationModel.add(new SubjectRegestrationModel("joo"));
        subjectRegestrationModel.add(new SubjectRegestrationModel("joo"));
        subjectRegestrationModel.add(new SubjectRegestrationModel("joo"));
        subjectRegestrationModel.add(new SubjectRegestrationModel("joo"));
        subjectRegestrationModel.add(new SubjectRegestrationModel("joo"));



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SubjectRegestrationAdapter(getApplicationContext(),subjectRegestrationModel));
        return recyclerView;
    }



}