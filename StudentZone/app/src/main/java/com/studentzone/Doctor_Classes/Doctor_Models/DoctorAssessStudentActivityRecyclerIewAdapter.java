package com.studentzone.Doctor_Classes.Doctor_Models;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Data_Base.Courses;
import com.studentzone.Data_Base.Departments;
import com.studentzone.Data_Base.Students;
import com.studentzone.Doctor_Classes.Doctor_Activities.DoctorStudentGrades;
import com.studentzone.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;

public class DoctorAssessStudentActivityRecyclerIewAdapter extends RecyclerView.Adapter<DoctorAssessStudentActivityRecyclerIewAdapter.doctorCoursesAssessViewHolder>
{
    private ArrayList<Courses> coursesList;


    /**
     * Constructor for the DoctorAssessStudentActivityRecyclerIewAdapter.
     * @param coursesList The list of courses to display.
     **********************************************************************************************/
      public DoctorAssessStudentActivityRecyclerIewAdapter(ArrayList<Courses> coursesList) {
          this.coursesList = coursesList;
      }

    /** onCreateViewHolder ()
     *  This method inflates the item layout for the courses and returns a new instance of the courseViewHolder class.
     *  Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     *   @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
     *   @param viewType The view type of the new View.
     *   @return A new ViewHolder that holds a View of the given view type.
     **********************************************************************************************/
    @NonNull
    @Override
    public doctorCoursesAssessViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_doctor_model_subject_grade,null,false);

        return new doctorCoursesAssessViewHolder(view);
    }

    /** onBindViewHolder ()
     *  IT's CallBack Method
     *  This method is called for each item in the list to bind the data to the view.
     *  It gets the doctor at the current position in the list and binds the course's data to the view holder.
     *  @param holder   The ViewHolder that should be updated to represent the contents of the item at the given position in the data set.
     *  @param position The position of the item within the adapter's data set.
     **********************************************************************************************/
    @Override
    public void onBindViewHolder(@NonNull @NotNull  DoctorAssessStudentActivityRecyclerIewAdapter.doctorCoursesAssessViewHolder holder, int position) {
        Courses course = coursesList.get(position);
        holder.bindCourseData(course);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Courses course = coursesList.get(position);

                    // Create an intent to open DoctorStudentGrades
                    Intent intent = new Intent(holder.itemView.getContext(), DoctorStudentGrades.class);
                    intent.putExtra("courseId", course.getId()+"");

                    // Start the activity
                    holder.itemView.getContext().startActivity(intent);
                }
            }
        });
    }


    /**getItemCount()
     * Returns the total number of items in the data set held by the adapter.
     * @return The total number of items in this adapter.
     **********************************************************************************************/
    @Override
    public int getItemCount() {
        return coursesList.size();
    }

    /** holder Class For doctorCourseRecyclerViewAdapter
     *  It holds references to the views in the item layout and binds the data to the views.
     **********************************************************************************************/
    static class doctorCoursesAssessViewHolder extends RecyclerView.ViewHolder  {
        TextView tv_course_name, tv_course_code, tv_first_letter_of_course;

        public doctorCoursesAssessViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_course_name = itemView.findViewById(R.id.activity_doctor_subject_grades_tv_sub_name);
            tv_course_code  = itemView.findViewById(R.id.activity_doctor_subject_grades_tv_sub_code);
            tv_first_letter_of_course  = itemView.findViewById(R.id.activity_doctor_subject_grades_tv_sn);

        }

        public void bindCourseData(Courses course) {

            String abbreviation = "";
            String courseName = course.getName();
            String[] words = courseName.split(" ");
            for (String word : words) {
                char firstLetter = word.charAt(0);
                abbreviation += firstLetter;
            }

            tv_course_name.setText(course.getName());
            tv_course_code.setText(course.getCode());
            tv_first_letter_of_course.setText(abbreviation.toUpperCase(Locale.ROOT));

        }



    }
}


