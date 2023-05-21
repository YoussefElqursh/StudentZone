package com.studentzone.Admin_Calsses.Admin_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.studentzone.Admin_Calsses.Admin_Models.Admin_Subject_Model.AdminSubjectAdapter;
import com.studentzone.Admin_Calsses.Admin_Models.Admin_Subject_Model.AdminSubjectModel;
import com.studentzone.Admin_Calsses.Admin_Models.Admin_Subject_Model.SelectListener;
import com.studentzone.R;

import java.util.ArrayList;
import java.util.List;

public class AdminAbsenceFilesActivity extends AppCompatActivity implements SelectListener {
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
        subjectModel.add(new AdminSubjectModel("Operating System-1","260",R.drawable.ic_book_1));
        subjectModel.add(new AdminSubjectModel("Software Engineering-1","1120",R.drawable.ic_book_1));
        subjectModel.add(new AdminSubjectModel("Software Engineering-2","500",R.drawable.ic_book_1));
        subjectModel.add(new AdminSubjectModel("Multmedia","3",R.drawable.ic_book_1));
        subjectModel.add(new AdminSubjectModel("Computer Programming-1","8",R.drawable.ic_book_1));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_book_1));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_book_1));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_book_1));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_book_1));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_book_1));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_book_1));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_book_1));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_book_1));
        subjectModel.add(new AdminSubjectModel("os","bad",R.drawable.ic_book_1));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdminSubjectAdapter(getApplicationContext(),subjectModel, this));
        return recyclerView;
    }

    @Override
    public void onItemClicked(AdminSubjectModel adminSubjectModel) {
        Toast.makeText(this, adminSubjectModel.getSubjectName(), Toast.LENGTH_SHORT).show();
    }
}