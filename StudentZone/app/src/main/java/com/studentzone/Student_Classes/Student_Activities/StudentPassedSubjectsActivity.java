package com.studentzone.Student_Classes.Student_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Models.StudentAdapter;
import com.studentzone.Student_Classes.Student_Models.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class StudentPassedSubjectsActivity extends AppCompatActivity {

    Button activity_admin_student_passed_subjects_btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_passed_subjects);
        backButtonAction();
        StudentPassedSubjectRecyclerView();
    }

    public void backButtonAction(){
        activity_admin_student_passed_subjects_btn_back = findViewById(R.id.activity_admin_student_passed_subjects_btn_back);
        activity_admin_student_passed_subjects_btn_back.setOnClickListener(v -> startActivity(new Intent(StudentPassedSubjectsActivity.this, StudentHomeActivity.class)));
    }

    public RecyclerView StudentPassedSubjectRecyclerView()
    {
        RecyclerView recyclerView = findViewById(R.id.student_passed_subjects_recycleview);

        List<StudentModel> studentPassedSubjectModel = new ArrayList<StudentModel>();
        studentPassedSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new StudentModel("os","bad",R.drawable.ic_students));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new StudentAdapter(getApplicationContext(),studentPassedSubjectModel));
        return recyclerView;
    }
}