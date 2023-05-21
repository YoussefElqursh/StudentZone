package com.studentzone.Doctor_Classes.Doctor_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.Doctor_Classes.Doctor_Model.DoctorSubjectAdapter;
import com.studentzone.Doctor_Classes.Doctor_Model.DoctorSubjectModel;
import com.studentzone.R;

import java.util.ArrayList;
import java.util.List;

public class DoctorSubjectsActivity extends AppCompatActivity {
    Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_subjects);
        buttonBackAction();
        DoctorSubjectRecyclerView();
    }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_doctor_subjects_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(DoctorSubjectsActivity.this, DoctorHomeActivity.class)));
    }

        public RecyclerView DoctorSubjectRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.doctor_subjects_recycleview);

        List<DoctorSubjectModel> doctorSubjectModel=new ArrayList<DoctorSubjectModel>();

        doctorSubjectModel.add(new DoctorSubjectModel("joo","joo",R.drawable.ic_book_1));
        doctorSubjectModel.add(new DoctorSubjectModel("joo","joo",R.drawable.ic_book_1));
        doctorSubjectModel.add(new DoctorSubjectModel("joo","joo",R.drawable.ic_book_1));
        doctorSubjectModel.add(new DoctorSubjectModel("joo","joo",R.drawable.ic_book_1));
        doctorSubjectModel.add(new DoctorSubjectModel("joo","joo",R.drawable.ic_book_1));
        doctorSubjectModel.add(new DoctorSubjectModel("joo","joo",R.drawable.ic_book_1));
        doctorSubjectModel.add(new DoctorSubjectModel("joo","joo",R.drawable.ic_book_1));
        doctorSubjectModel.add(new DoctorSubjectModel("joo","joo",R.drawable.ic_book_1));
        doctorSubjectModel.add(new DoctorSubjectModel("joo","joo",R.drawable.ic_book_1));
        doctorSubjectModel.add(new DoctorSubjectModel("joo","joo",R.drawable.ic_book_1));
        doctorSubjectModel.add(new DoctorSubjectModel("joo","joo",R.drawable.ic_book_1));
        doctorSubjectModel.add(new DoctorSubjectModel("joo","joo",R.drawable.ic_book_1));
        doctorSubjectModel.add(new DoctorSubjectModel("joo","joo",R.drawable.ic_book_1));
        doctorSubjectModel.add(new DoctorSubjectModel("joo","joo",R.drawable.ic_book_1));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DoctorSubjectAdapter(getApplicationContext(),doctorSubjectModel));
        return recyclerView;
    }


}