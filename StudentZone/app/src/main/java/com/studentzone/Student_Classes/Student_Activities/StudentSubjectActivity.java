package com.studentzone.Student_Classes.Student_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Model.StudentAdapter;
import com.studentzone.Student_Classes.Student_Model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class StudentSubjectActivity extends AppCompatActivity {
    Button activity_student_subject_btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_subject);
        backButtonAction();
        StudentSubjectRecyclerView();
        }

    public void backButtonAction(){
        activity_student_subject_btn_back = findViewById(R.id.activity_student_subject_btn_back);
        activity_student_subject_btn_back.setOnClickListener(v -> startActivity(new Intent(StudentSubjectActivity.this, StudentHomeActivity.class)));

    }

    public RecyclerView StudentSubjectRecyclerView()
    {
        RecyclerView recyclerView = findViewById(R.id.student_subject_recycleview);

        List<StudentModel>studentSubjectModel = new ArrayList<StudentModel>();
        studentSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new StudentAdapter(getApplicationContext(),studentSubjectModel));
        return recyclerView;
    }
}