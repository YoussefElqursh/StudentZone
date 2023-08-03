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
        studentPassedSubjectModel.add(new SubjectModel("Opearing System-1","15asda"));
        studentPassedSubjectModel.add(new SubjectModel("Machine Learning","25dsf3"));
        studentPassedSubjectModel.add(new SubjectModel("Computer Programming-1","4576as"));
        studentPassedSubjectModel.add(new SubjectModel("Software Engineering","sdf257"));
        studentPassedSubjectModel.add(new SubjectModel("Mathimatics-3","595sdf"));
        studentPassedSubjectModel.add(new SubjectModel("Multimedia","d5e8r2"));
        studentPassedSubjectModel.add(new SubjectModel("Information System","c5d9sd"));
        studentPassedSubjectModel.add(new SubjectModel("Introduction","9sd64s"));
        studentPassedSubjectModel.add(new SubjectModel("Opearing System-2","sdaf86"));
        studentPassedSubjectModel.add(new SubjectModel("Computer Programming-2","74aswe"));
        studentPassedSubjectModel.add(new SubjectModel("Mathimatics-2","123sdf"));
        studentPassedSubjectModel.add(new SubjectModel("Computer Programming-3","69sder"));
        studentPassedSubjectModel.add(new SubjectModel("Mobile Application","r6s95f"));
        studentPassedSubjectModel.add(new SubjectModel("Mathimatics-1","123dfg"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SubjectAdapter(getApplicationContext(), (ArrayList<SubjectModel>) studentPassedSubjectModel));
        return recyclerView;
    }
}