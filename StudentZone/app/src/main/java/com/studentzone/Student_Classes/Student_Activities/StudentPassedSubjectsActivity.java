package com.studentzone.Student_Classes.Student_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.SubjectAdapter;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.SubjectModel;

import java.util.ArrayList;
import java.util.List;

public class StudentPassedSubjectsActivity extends AppCompatActivity {

    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_passed_subjects);
        buttonBackAction();
        studentPassedSubjectRecyclerView();
    }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_student_passed_subjects_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(StudentPassedSubjectsActivity.this, StudentHomeActivity.class)));
    }

    public RecyclerView studentPassedSubjectRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.student_passed_subjects_recycleview);

        List<SubjectModel> studentPassedSubjectModel = new ArrayList<SubjectModel>();
        studentPassedSubjectModel.add(new SubjectModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new SubjectModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new SubjectModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new SubjectModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new SubjectModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new SubjectModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new SubjectModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new SubjectModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new SubjectModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new SubjectModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new SubjectModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new SubjectModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new SubjectModel("os","bad",R.drawable.ic_students));
        studentPassedSubjectModel.add(new SubjectModel("os","bad",R.drawable.ic_students));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SubjectAdapter(getApplicationContext(),studentPassedSubjectModel));
        return recyclerView;
    }
}