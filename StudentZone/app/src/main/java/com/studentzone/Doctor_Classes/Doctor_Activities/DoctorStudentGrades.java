package com.studentzone.Doctor_Classes.Doctor_Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.Admin_Classes.Admin_Activities.AdminDoctorsAccountsActivity;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.Data_Base.Students;
import com.studentzone.Doctor_Classes.Doctor_Models.DoctorStudentsGradesRecyclerViewAdapter;
import com.studentzone.R;

import java.util.ArrayList;

public class DoctorStudentGrades extends AppCompatActivity {

    private Button btn_show_search, btn_hide_search;
    private EditText et_search;
    private Toolbar toolbar;
    private LinearLayout ll_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_students_assessment);

        setBackButtonAction();
        displayAllStudents();

//        setButtonSearchAction(); // show search et
//        setButtonBackSearchAction(); // hide search et

    }

    /** initializeViews()
     *  inflate
     **********************************************************************************************/
    public void initializeViews() {
        btn_show_search = findViewById(R.id.activity_doctor_student_degree_btn_search);
        btn_hide_search = findViewById(R.id.activity_doctor_student_degree_btn_search_back);

        et_search = findViewById(R.id.activity_doctor_student_degree_et_search);

        toolbar = findViewById(R.id.activity_doctor_student_degree_tbar);
        ll_search = findViewById(R.id.activity_doctor_student_degree_ll_search);
    }
    public void setBackButtonAction(){
        Button btn_back = findViewById(R.id.activity_doctor_assessStudent_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(DoctorStudentGrades.this, DoctorAssessStudentActivity.class)));
    }

    /**displayAllStudents()
     **********************************************************************************************/
    public void displayAllStudents() {

        My_DB db = new My_DB(getBaseContext());

        String courseId = getIntent().getStringExtra("courseId");
        int courseID = Integer.parseInt(courseId);

        ArrayList<Students> studentsArrayList = db.getEnrolledStudentsByCourseId(courseID);
        RecyclerView studentRecyclerView = findViewById(R.id.activity_doctor_assessStudent_recycleView);

        DoctorStudentsGradesRecyclerViewAdapter adapter = new DoctorStudentsGradesRecyclerViewAdapter(studentsArrayList, this);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        studentRecyclerView.setHasFixedSize(true);
        studentRecyclerView.setLayoutManager(lm);
        studentRecyclerView.setAdapter(adapter);
    }

    /**setButtonSearchAction()
     * Make button search show search edit text and hide toolbar
     **********************************************************************************************/
    public void setButtonSearchAction() {

        btn_show_search.setOnClickListener(v -> {

            toolbar.setVisibility(View.INVISIBLE);
            ll_search.setVisibility(View.VISIBLE);

            //Show make a cursor focus on edit text when click on search button
            et_search.requestFocus(v.getTextDirection());

            //Show keyboard INPUT METHOD SERVICE when click on search button
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(et_search, InputMethodManager.SHOW_IMPLICIT);

            //Make animation when click on search btn
            Animation animation = AnimationUtils.loadAnimation(DoctorStudentGrades.this, R.anim.anim_activities_show_search);
            ll_search.startAnimation(animation);

        });
    }

    /**setButtonBackSearchAction()
     * Make button back Search hide search edit text and show toolbar
     **********************************************************************************************/
    public void setButtonBackSearchAction() {
        btn_hide_search.setOnClickListener(v -> {

            toolbar.setVisibility(View.VISIBLE);
            ll_search.setVisibility(View.INVISIBLE);

            et_search.setText("");
            //Make animation when click on search back
            Animation animation = AnimationUtils.loadAnimation(DoctorStudentGrades.this, R.anim.anim_activities_hide_search);
            toolbar.startAnimation(animation);

            //Close Keyboard INPUT METHOD SERVICE when click on search button
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);

        });
    }

}
