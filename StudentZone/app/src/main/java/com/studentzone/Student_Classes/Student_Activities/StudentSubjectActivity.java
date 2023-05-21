package com.studentzone.Student_Classes.Student_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.SubjectAdapter;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.SubjectModel;

import java.util.ArrayList;
import java.util.List;

public class StudentSubjectActivity extends AppCompatActivity {
    My_DB my_db=new My_DB(this);
    Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_subject);
        buttonBackAction();
        ArrayList<String> course_regester=my_db.Get_courses_regester_for_student();

        studentSubjectRecyclerView(course_regester);
        }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_student_subject_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(StudentSubjectActivity.this, StudentHomeActivity.class)));
    }

    public RecyclerView studentSubjectRecyclerView(ArrayList v)
    {
        RecyclerView recyclerView = findViewById(R.id.student_subject_recycleview);

        List<SubjectModel>studentSubjectModel = new ArrayList<SubjectModel>();
        for (int i = 0; i < v.size(); i++) {
            studentSubjectModel.add(new SubjectModel((String) v.get(i),"good",R.drawable.ic_students));

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SubjectAdapter(getApplicationContext(),studentSubjectModel));
        return recyclerView;
    }
}