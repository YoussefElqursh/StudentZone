package com.studentzone.Student_Classes.Student_Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Models.RegestrationModel.SubjectRegestrationAdapter;
import com.studentzone.Student_Classes.Student_Models.RegestrationModel.SubjectRegestrationModel;

import java.util.ArrayList;

public class StudentRegistrationActivity extends AppCompatActivity  {

    private LinearLayout ll_no_search_results;
    private RecyclerView coursesRecyclerView;
    Button btn_back;

    RecyclerView recyclerView;
    ArrayList<SubjectRegestrationModel>arrayList;
    ArrayList<SubjectRegestrationModel> subjectsWithPrerequisiteRegistered = new ArrayList<>();


    //store in the recycler
    Button btn_After_Registration;

    My_DB my_db=new My_DB( this);



    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        ll_no_search_results = findViewById(R.id.activity_student_subject_ll_no_subjects);
        coursesRecyclerView = findViewById(R.id.recycleview_student_regestration_choose_subject);


        btn_After_Registration=  findViewById(R.id.fragment_new_department_btn_save);
                buttonBackAction();
        recyclerView=findViewById(R.id.recycleview_student_regestration_choose_subject );//add item in recycler
        if(my_db.getSumOfSubjectHours_as_all()>=63){
            if(my_db.studentCs()==2){
                arrayList=my_db.getCourses_for_students_level3_not_havePre();
                arrayList.addAll(my_db.getCourses_for_students_level2());
                subjectsWithPrerequisiteRegistered.addAll(my_db.getCourses_for_students_level3_havePre());
                for (SubjectRegestrationModel model : subjectsWithPrerequisiteRegistered) {
                    int pre_id = my_db.getPreRequestIdBy_Name(model.getSubjectName());
                    boolean prerequisiteRegistered = my_db.pre_thereExist_inEnrollment_course_id(pre_id);
                    if (prerequisiteRegistered&&my_db.getSubjectDegree_Id(pre_id)>=50) {

                        arrayList.add(model);
                    }
                }

            }
        } else if (my_db.getSumOfSubjectHours_as_all()>=27) {
            arrayList=my_db.getCourses_for_students_level2_not_havePre();
            arrayList.addAll(my_db.getCourses_for_students_level1());
            subjectsWithPrerequisiteRegistered.addAll(my_db.getCourses_for_students_level2_havePre());
            for (SubjectRegestrationModel model : subjectsWithPrerequisiteRegistered) {
                int pre_id = my_db.getPreRequestIdBy_Name(model.getSubjectName());
                boolean prerequisiteRegistered = my_db.pre_thereExist_inEnrollment_course_id(pre_id);

                if (prerequisiteRegistered&&my_db.getSubjectDegree_Id(pre_id)>=50) {

                    arrayList.add(model);
                }

            }

        } else  {

            arrayList = my_db.getCourses_for_students_level1_not_havePre(); // Method to get all courses name and course code
            subjectsWithPrerequisiteRegistered.addAll(my_db.getCourses_for_students_level1_havePre());
            for (SubjectRegestrationModel model : subjectsWithPrerequisiteRegistered) {

                int pre_id = my_db.getPreRequestIdBy_Name(model.getSubjectName());
                boolean prerequisiteRegistered = my_db.pre_thereExist_inEnrollment_course_id(pre_id);

                if (prerequisiteRegistered&&my_db.getSubjectDegree_Id(pre_id)>=50) {
                    arrayList.add(model);
                }

        }

        }
        SubjectRegestrationAdapter subjectRegestrationAdapter=new SubjectRegestrationAdapter(this,arrayList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(subjectRegestrationAdapter); //add model to recycler view


        btn_After_Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(subjectRegestrationAdapter.getCourse_id()!=null&&!subjectRegestrationAdapter.getCourse_id().isEmpty())
                {
                    ArrayList<Integer> CourseId=subjectRegestrationAdapter.getCourse_id();
                    for (Integer id : CourseId) {
                        my_db.insertEnrollmentTable(id);
                    }
                    startActivity(new Intent(StudentRegistrationActivity.this,StudentSubjectActivity.class));
                    recreate();
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(StudentRegistrationActivity.this);
                    builder.setTitle("Watch Out!");
                    builder.setMessage("you have to regist atleast one subject .");
                    builder.setNegativeButton("Ok", null);
                    builder.show();

                }









            }
        });



        updateNoCoursesVisibility();



    }


    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_student_regestration_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(StudentRegistrationActivity.this, StudentHomeActivity.class)));
    }

    //this lines to show another activity to student if there is no courses to enrolled
    public void updateNoCoursesVisibility() {

        if(arrayList.size() == 0){
            coursesRecyclerView.setVisibility(View.INVISIBLE);
            ll_no_search_results.setVisibility(View.VISIBLE);
        }
        else {
            coursesRecyclerView.setVisibility(View.VISIBLE);
            ll_no_search_results.setVisibility(View.INVISIBLE);
        }
    }
}