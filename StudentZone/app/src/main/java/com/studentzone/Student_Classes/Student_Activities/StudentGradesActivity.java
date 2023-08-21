package com.studentzone.Student_Classes.Student_Activities;

import static androidx.fragment.app.FragmentPagerAdapter.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Fragments.FourthLevelFragment;
import com.studentzone.Student_Classes.Student_Fragments.FristLevelFragment;
import com.studentzone.Student_Classes.Student_Fragments.SecondLevelFragment;
import com.studentzone.Student_Classes.Student_Fragments.ThirdLevelFragment;
import com.studentzone.Student_Classes.Student_Fragments.ViewPagerAdapter;
import com.studentzone.Student_Classes.Student_Models.RegestrationModel.SubjectRegestrationAdapter;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.StudentPassedModel;

import java.util.ArrayList;
public class StudentGradesActivity extends AppCompatActivity {

    Button btn_back;
    TabLayout tabLayout;
    TextView NumberOfHours;
    ViewPager viewPager;
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


    }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_student_grades_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(StudentGradesActivity.this, StudentHomeActivity.class)));
    }

    public void openFragments()
    {
        tabLayout = findViewById(R.id.activity_student_grades_tab);
        viewPager = findViewById(R.id.activity_student_grades_viewpager);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        viewPagerAdapter.addFragment(new FristLevelFragment(),"Level 1");
        viewPagerAdapter.addFragment(new SecondLevelFragment(),"Level 2");
        viewPagerAdapter.addFragment(new ThirdLevelFragment(),"Level 3");
        viewPagerAdapter.addFragment(new FourthLevelFragment(),"Level 4");

        viewPager.setAdapter(viewPagerAdapter);
    }

}