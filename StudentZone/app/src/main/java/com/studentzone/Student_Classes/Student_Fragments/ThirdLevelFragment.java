package com.studentzone.Student_Classes.Student_Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.StudentPassedModel;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.StudentPassedSubjectsAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ThirdLevelFragment extends Fragment {
    TextView Hours,Gpa;
    My_DB my_db;
    ArrayList<StudentPassedModel> arrayList3 =new ArrayList<>();
    RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_third_level, container, false);
        my_db=new My_DB(getActivity());
//        Hours=view.findViewById(R.id.current_hours);
//        Gpa=view.findViewById(R.id.Current_Gpa);
        String Total_Hours=String.valueOf(my_db.getSumOfSubjectHourslevel_3());
//        Hours.setText(Total_Hours);
        recyclerView=view.findViewById(R.id.student_grades_l3_recycleview);
        arrayList3=my_db.getPassedCoursesForStudents_Level_3();
        StudentPassedSubjectsAdapter subjectAdapter=new StudentPassedSubjectsAdapter(getContext(),arrayList3);
//        float gpa = (float) calculateGPA(arrayList3);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
//        String formattedGpa = decimalFormat.format(gpa);

//        Gpa.setText(formattedGpa);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(subjectAdapter);
        return view;

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    public double calculateGPA(ArrayList<StudentPassedModel> passedSubjects) {
        double totalCredits = 0.0;
        double totalGradePoints = 0.0;

        for (StudentPassedModel subject : passedSubjects) {
            String SubjectName = subject.getSubjectName(); // Assuming you have a method to retrieve the credits for each subject
            int Subject_id=my_db.get_Id_course_by_CourseName(SubjectName);
            int credits= my_db.getCreditbySubjectName(Subject_id);

            String grade =subject.getgrade();// Assuming you have a method to retrieve the grade for each subject

            double gradePoint = convertGradeToGPA(grade);
            double subjectGradePoints = credits * gradePoint;

            totalCredits += credits;
            totalGradePoints += subjectGradePoints;
        }

        if (totalCredits == 0.0) {
            return 0.0; // To avoid division by zero
        }

        double gpa = totalGradePoints / totalCredits;
        return gpa;
    }

    private double convertGradeToGPA(String grade) {
        switch (grade) {
            case "A+":
                return 4.0;
            case "A":
                return 3.7;
            case "B+":
                return 3.3;
            case "B":
                return 3.0;
            case "C+":
                return 2.7;
            case "C":
                return 2.4;
            case "D+":
                return 2.2;
            case "D":
                return 2.0;
            default:
                return 0.0; // Assuming unrecognized grades are considered as F (0.0)
        }
    }
}