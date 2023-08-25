package com.studentzone.Doctor_Classes.Doctor_Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Data_Base.Courses;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;

public class DoctorCoursesRecyclerViewAdapter extends RecyclerView.Adapter<DoctorCoursesRecyclerViewAdapter.doctorCoursesViewHolder>
{

    private final ArrayList<Courses> coursesList;
    private final My_DB db;
    private BottomSheetDialog bottomSheetDialog;
    private View bottomSheetDialogView;


    /**
     * Constructor for the CourseRecyclerViewAdapter.
     *
     * @param context     The context of the adapter.
     * @param coursesList The list of courses to display.
     **********************************************************************************************/
    public DoctorCoursesRecyclerViewAdapter(Context context, ArrayList<Courses> coursesList) {
        this.coursesList = coursesList;
        this.db = new My_DB(context);
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
    public doctorCoursesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_doctor_model_subject,null,false);

        return new doctorCoursesViewHolder(view);
    }

    /** onBindViewHolder ()
     *  IT's CallBack Method
     *  This method is called for each item in the list to bind the data to the view.
     *  It gets the doctor at the current position in the list and binds the course's data to the view holder.
     *  @param holder   The ViewHolder that should be updated to represent the contents of the item at the given position in the data set.
     *  @param position The position of the item within the adapter's data set.
     **********************************************************************************************/
    @Override
    public void onBindViewHolder(@NonNull @NotNull DoctorCoursesRecyclerViewAdapter.doctorCoursesViewHolder holder, int position) {

        Courses course = coursesList.get(position);

        holder.bindCourseData(course);

        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);

        holder.itemView.startAnimation(animation);
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
    class doctorCoursesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_course_name, tv_course_code, tv_first_letter_of_course;
        ImageButton ib_info;
        public doctorCoursesViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_course_name = itemView.findViewById(R.id.activity_doctor_subject_tv_sub_name);
            tv_course_code  = itemView.findViewById(R.id.activity_doctor_subject_tv_sub_code);
            tv_first_letter_of_course  = itemView.findViewById(R.id.activity_doctor_subject_tv_sn);

            ib_info = itemView.findViewById(R.id.activity_doctor_subject_ibtn_info);

            ib_info.setOnClickListener(this);
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

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Courses course = coursesList.get(position);
                displayCourseDetailsDialog(course);
            }
        }

        /** displayCourseDetailsDialog ()
         *  This method shows a bottom sheet dialog with the details of a Department when the user clicks on the Department's card view.
         *  It inflates a layout for displaying the details of a Department and sets the text of the views in the layout to the corresponding data of the Department.
         *  It also sets a click listener on the close button to dismiss the dialog.
         **********************************************************************************************/
        private void displayCourseDetailsDialog(Courses course) {
            bottomSheetDialog = new BottomSheetDialog(itemView.getContext(), R.style.BottomSheetStyle);
            bottomSheetDialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.fragment_doctor_info_subject, null, false);

            EditText et_courseName_info = bottomSheetDialogView.findViewById(R.id.fragment_doctor_subjects_info_et_name);
            EditText et_courseCode_info = bottomSheetDialogView.findViewById(R.id.fragment_doctor_subjects_info_et_code);
            EditText et_courseDepartment_info = bottomSheetDialogView.findViewById(R.id.fragment_doctor_subjects_info_et_department);
            EditText et_coursePreRequest_info = bottomSheetDialogView.findViewById(R.id.fragment_doctor_subjects_info_et_previous);
            EditText et_courseLevel_info = bottomSheetDialogView.findViewById(R.id.fragment_doctor_subjects_info_et_level);
            EditText et_courseHours_info = bottomSheetDialogView.findViewById(R.id.fragment_doctor_subjects_info_et_hours);
            EditText et_courseNumberOfStudents_info = bottomSheetDialogView.findViewById(R.id.fragment_doctor_subjects_info_et_num_of_students);
            Button btn_close_course_info = bottomSheetDialogView.findViewById(R.id.fragment_doctor_subjects_info_btn_close);

            // Set the text of the views to the corresponding data of the course
            et_courseName_info.setText(course.getName());
            et_courseCode_info.setText(course.getCode());
            et_courseDepartment_info.setText(db.getDepartmentNameById(course.getDepartment()));
            et_coursePreRequest_info.setText(db.getPreRequestNameById(course.getPreRequest()));
            et_courseLevel_info.setText(String.valueOf(course.getLevel()));
            et_courseHours_info.setText(String.valueOf(course.getNumberOfHours()));

            et_courseNumberOfStudents_info.setText(String.valueOf(db.getEnrolledStudentCountByCourseId(course.getId())));

            // Set a click listener on the close button to dismiss the dialog
            btn_close_course_info.setOnClickListener(v -> bottomSheetDialog.dismiss());

            bottomSheetDialog.setContentView(bottomSheetDialogView);
            bottomSheetDialog.show();
        }

    }
}


