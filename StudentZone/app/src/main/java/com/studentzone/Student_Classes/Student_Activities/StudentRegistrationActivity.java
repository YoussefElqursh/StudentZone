package com.studentzone.Student_Classes.Student_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.Admin_Calsses.Admin_Activities.AdminDepartmentsActivity;
import com.studentzone.Admin_Calsses.Admin_Activities.AdminHomeActivity;
import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Models.RegestrationModel.SubjectRegestrationAdapter;
import com.studentzone.Student_Classes.Student_Models.RegestrationModel.SubjectRegestrationModel;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.SubjectAdapter;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.SubjectModel;

import java.util.ArrayList;
import java.util.List;

public class StudentRegistrationActivity extends AppCompatActivity {

    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        buttonBackAction();
        SubjectRegestrationRecyclerView();
        SubjectRegestedRecyclerView();
    }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_student_regestration_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(StudentRegistrationActivity.this, StudentHomeActivity.class)));
    }

    public RecyclerView SubjectRegestrationRecyclerView()
    {
        RecyclerView recyclerView = findViewById(R.id.recycleview_student_regestration_choose_subject);

        List<SubjectRegestrationModel>subjectRegestrationModel=new ArrayList<SubjectRegestrationModel>();
        subjectRegestrationModel.add(new SubjectRegestrationModel("joo"));
        subjectRegestrationModel.add(new SubjectRegestrationModel("joo"));
        subjectRegestrationModel.add(new SubjectRegestrationModel("joo"));
        subjectRegestrationModel.add(new SubjectRegestrationModel("joo"));
        subjectRegestrationModel.add(new SubjectRegestrationModel("joo"));
        subjectRegestrationModel.add(new SubjectRegestrationModel("joo"));
        subjectRegestrationModel.add(new SubjectRegestrationModel("joo"));
        subjectRegestrationModel.add(new SubjectRegestrationModel("joo"));
        subjectRegestrationModel.add(new SubjectRegestrationModel("joo"));

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