package com.studentzone.Student_Classes.Student_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Fragments.FourthLevelFragment;
import com.studentzone.Student_Classes.Student_Fragments.FristLevelFragment;
import com.studentzone.Student_Classes.Student_Fragments.SecondLevelFragment;
import com.studentzone.Student_Classes.Student_Fragments.ThirdLevelFragment;
import com.studentzone.Student_Classes.Student_Models.RegestrationModel.SubjectRegestrationAdapter;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.StudentPassedModel;

import java.util.ArrayList;
public class StudentGradesActivity extends AppCompatActivity {

    Button btn_back , btn_level_1 , btn_level_2 , btn_level_3 , btn_level_4;


    RecyclerView recyclerView;
    ArrayList<StudentPassedModel> arrayList;
    //store in the recycler
    Button btn_After_Registration;
    SubjectRegestrationAdapter subjectRegestrationAdapter;
    My_DB my_db=new My_DB( this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_grades);
        buttonBackAction();
        openFragments();

//        recyclerView=findViewById(R.id.student_passed_subjects_recycleview);//add item in recycler
//        arrayList=my_db.getPassedCoursesForStudents();//Method to get all courses name and courses code
//        StudentPassedSubjectsAdapter studentPassedSubjectsAdapter=new StudentPassedSubjectsAdapter(this,arrayList);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        recyclerView.setAdapter(studentPassedSubjectsAdapter); //add model to recycler view

    }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_student_grades_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(StudentGradesActivity.this, StudentHomeActivity.class)));
    }


    public void openFragments()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        btn_level_1 = findViewById(R.id.activity_student_grades_btn_l1);
        btn_level_2 = findViewById(R.id.activity_student_grades_btn_l2);
        btn_level_3 = findViewById(R.id.activity_student_grades_btn_l3);
        btn_level_4 = findViewById(R.id.activity_student_grades_btn_l4);

        btn_level_1.setOnClickListener(v -> fragmentManager.beginTransaction()
                .replace(R.id.fragment_student_grades_ll_main, FristLevelFragment.class,null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit());

        btn_level_2.setOnClickListener(v -> fragmentManager.beginTransaction()
                .replace(R.id.fragment_student_grades_ll_main, SecondLevelFragment.class,null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit());

        btn_level_3.setOnClickListener(v -> fragmentManager.beginTransaction()
                .replace(R.id.fragment_student_grades_ll_main, ThirdLevelFragment.class,null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit());

        btn_level_4.setOnClickListener(v -> fragmentManager.beginTransaction()
                .replace(R.id.fragment_student_grades_ll_main, FourthLevelFragment.class,null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit());
    }

}