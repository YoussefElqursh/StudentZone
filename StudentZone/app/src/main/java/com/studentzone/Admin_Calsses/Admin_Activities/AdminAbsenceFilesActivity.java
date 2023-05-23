package com.studentzone.Admin_Calsses.Admin_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.Admin_Calsses.Admin_Models.Admin_Subject_Model.AdminSubjectAdapter;
import com.studentzone.Admin_Calsses.Admin_Models.Admin_Subject_Model.AdminSubjectModel;
import com.studentzone.R;

import java.util.ArrayList;
import java.util.List;

public class AdminAbsenceFilesActivity extends AppCompatActivity {
    Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_absence_files);
        buttonBackAction();
        DoctorAbsenceFileRecyclerView();
    }
    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_admin_absence_files_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(AdminAbsenceFilesActivity.this,AdminHomeActivity.class)));
    }

    public RecyclerView DoctorAbsenceFileRecyclerView()
    {
        RecyclerView recyclerView = findViewById(R.id.activity_admin_absence_file_subject_recyclerview);

        List<AdminSubjectModel> subjectModel = new ArrayList<AdminSubjectModel>();
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_pdf_file));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_pdf_file));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_pdf_file));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_pdf_file));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_pdf_file));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_pdf_file));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_pdf_file));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_pdf_file));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_pdf_file));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_pdf_file));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_pdf_file));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_pdf_file));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_pdf_file));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_pdf_file));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdminSubjectAdapter(getApplicationContext(),subjectModel));
        return recyclerView;
    }

}