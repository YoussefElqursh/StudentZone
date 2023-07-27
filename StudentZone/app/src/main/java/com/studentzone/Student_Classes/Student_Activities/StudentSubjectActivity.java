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

public class StudentSubjectActivity extends AppCompatActivity {
    Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_subject);
        buttonBackAction();
        studentSubjectRecyclerView();
        }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_student_subject_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(StudentSubjectActivity.this, StudentHomeActivity.class)));
    }

    public RecyclerView studentSubjectRecyclerView()
    {
        RecyclerView recyclerView = findViewById(R.id.student_subject_recycleview);

        List<SubjectModel>studentSubjectModel = new ArrayList<SubjectModel>();
        studentSubjectModel.add(new SubjectModel("Opearing System-1","15asda"));
        studentSubjectModel.add(new SubjectModel("Machine Learning","25dsf3"));
        studentSubjectModel.add(new SubjectModel("Computer Programming-1","4576as"));
        studentSubjectModel.add(new SubjectModel("Software Engineering","sdf257"));
        studentSubjectModel.add(new SubjectModel("Mathimatics-3","595sdf"));
        studentSubjectModel.add(new SubjectModel("Multimedia","d5e8r2"));
        studentSubjectModel.add(new SubjectModel("Information System","c5d9sd"));
        studentSubjectModel.add(new SubjectModel("Introduction","9sd64s"));
        studentSubjectModel.add(new SubjectModel("Opearing System-2","sdaf86"));
        studentSubjectModel.add(new SubjectModel("Computer Programming-2","74aswe"));
        studentSubjectModel.add(new SubjectModel("Mathimatics-2","123sdf"));
        studentSubjectModel.add(new SubjectModel("Computer Programming-3","69sder"));
        studentSubjectModel.add(new SubjectModel("Mobile Application","r6s95f"));
        studentSubjectModel.add(new SubjectModel("Mathimatics-1","123dfg"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SubjectAdapter(getApplicationContext(),studentSubjectModel));
        return recyclerView;
    }
}