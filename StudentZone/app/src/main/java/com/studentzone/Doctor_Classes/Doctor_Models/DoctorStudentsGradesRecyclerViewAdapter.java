package com.studentzone.Doctor_Classes.Doctor_Models;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Data_Base.Enrollments;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.Data_Base.Students;
import com.studentzone.R;

import java.util.ArrayList;

public class DoctorStudentsGradesRecyclerViewAdapter extends RecyclerView.Adapter<DoctorStudentsGradesRecyclerViewAdapter.doctorStudentsGradesViewHolder> {

    private final ArrayList<Students> studentsList;
    private final My_DB db;

    /**
     * Constructor for the DoctorStudentsGradesRecyclerViewAdapter.
     *
     * @param studentsList The list of students who's enrolled in the specific course to display.
     **********************************************************************************************/
    public DoctorStudentsGradesRecyclerViewAdapter(ArrayList<Students> studentsList, Context context) {
        this.studentsList = studentsList;
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
    public doctorStudentsGradesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_doctor_model_student_grade,null,false);

        return new doctorStudentsGradesViewHolder(view);
    }

    /** onBindViewHolder ()
     *  IT's CallBack Method
     *  This method is called for each item in the list to bind the data to the view.
     *  It gets the doctor at the current position in the list and binds the course's data to the view holder.
     *  @param holder   The ViewHolder that should be updated to represent the contents of the item at the given position in the data set.
     *  @param position The position of the item within the adapter's data set.
     **********************************************************************************************/
    @Override
    public void onBindViewHolder(@NonNull DoctorStudentsGradesRecyclerViewAdapter.doctorStudentsGradesViewHolder holder, int position) {
        Students students = studentsList.get(position);
        holder.bindStudentData(students);


    }

    /**getItemCount()
     * Returns the total number of items in the data set held by the adapter.
     * @return The total number of items in this adapter.
     **********************************************************************************************/
    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    /** holder Class For DoctorStudentsGradesRecyclerViewAdapter
     *  It holds references to the views in the item layout and binds the data to the views.
     **********************************************************************************************/
    public class doctorStudentsGradesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_student_name, tv_student_degree,tv_student_grade;
        ImageView iv_student_icon;

        public doctorStudentsGradesViewHolder(@NonNull View itemView) {
            super(itemView);

            // Get the course ID from the intent


            tv_student_name = itemView.findViewById(R.id.activity_admin_student_tv_name);
            tv_student_degree = itemView.findViewById(R.id.activity_admin_student_tv_100);
            tv_student_grade = itemView.findViewById(R.id.activity_doctor_student_grade_ibtn_more);
            iv_student_icon = itemView.findViewById(R.id.activity_admin_student_iv);
        }

        public void bindStudentData(Students students) {

            String courseId = ((Activity) itemView.getContext()).getIntent().getStringExtra("courseId");
            int courseID = Integer.parseInt(courseId);
            Enrollments degreeAndGrade = db.getDegreeByCourseAndStudentId(courseID, students.getId());

            tv_student_name.setText(students.getFName());
            tv_student_grade.setText(degreeAndGrade.getStudent_grade());

            // Set the text fields with student data
            if (degreeAndGrade.getStudent_grade().equals("N")) {
                tv_student_degree.setHint("Not Corrected");
            } else {
                tv_student_degree.setText(String.valueOf(degreeAndGrade.getStudent_degree()));
            }

            if(students.getGender() != null  && students.getGender().equals("Male"))
                iv_student_icon.setImageResource(R.drawable.ic_male_student);
            else
                iv_student_icon.setImageResource(R.drawable.ic_female_student);

            itemView.setOnClickListener(v -> displaySetDegreeDialog(students));
        }

        @Override
        public void onClick(View v) {

        }
        /** displaySetDegreeDialog ()
         *  This method shows a bottom sheet dialog with the details of a Student when the user(doctor) clicks on the Student's card view.
         *  It inflates a layout for displaying the details of a Student and sets the text of the views in the layout to the corresponding data of the Student.
         *  It also sets a click listener on the close button to dismiss the dialog.
         **********************************************************************************************/
        private void displaySetDegreeDialog(Students students) {
            // Create a BottomSheetDialog instance
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(itemView.getContext(), R.style.BottomSheetStyle);

            // Inflate the layout for the dialog
            View bottomSheetDialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.fragment_doctor_add_student_grade, null, false);

            // Find the views in the layout
            TextView et_studentName = bottomSheetDialogView.findViewById(R.id.fragment_doctor_add_student_grade_et_student_name);
            TextView et_studentAid = bottomSheetDialogView.findViewById(R.id.fragment_doctor_add_student_grade_et_student_aid);
            TextView et_studentScore = bottomSheetDialogView.findViewById(R.id.fragment_doctor_add_student_grade_et_student_score);
            TextView et_studentGrad = bottomSheetDialogView.findViewById(R.id.fragment_doctor_add_student_grade_et_student_grade);
            Button btn_save = bottomSheetDialogView.findViewById(R.id.fragment_doctor_add_student_grade_btn_save);
            Button btn_close = bottomSheetDialogView.findViewById(R.id.fragment_doctor_add_student_grade_btn_close);

            // Get the course ID from the intent
            String courseId = ((Activity) itemView.getContext()).getIntent().getStringExtra("courseId");
            int courseID = Integer.parseInt(courseId);

            // Get the degree and grade for the given course and student
            Enrollments degreeAndGrade = db.getDegreeByCourseAndStudentId(courseID, students.getId());

            // Keep track of the original data (grade and degree)
            String originalDegree = String.valueOf(degreeAndGrade.getStudent_degree());
            String originalGrade = degreeAndGrade.getStudent_grade();

            // Set the text fields with student data
            et_studentName.setText(students.getFName());
            if (originalGrade.equals("N")) {
                et_studentScore.setHint("Not Corrected");
            } else {
                et_studentScore.setText(String.valueOf(degreeAndGrade.getStudent_degree()));
            }
            et_studentAid.setText(students.getAcademic_Number());
            et_studentGrad.setText(originalGrade);

            // Disable the save button until the data is modified
            btn_save.setEnabled(false);

            // Create a TextWatcher to monitor changes in the score text field
            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    // Enable the save button if the text has changed from the original value
                    boolean dataChanged = (!et_studentScore.getText().toString().equals(originalDegree));

                    if (TextUtils.isEmpty(et_studentScore.getText().toString().trim())) {
                        et_studentGrad.setText("N"); // Exam not corrected yet
                        return;
                    }

                    btn_save.setEnabled(dataChanged);

                    String input = s.toString();
                    if (!input.isEmpty()) {
                        try {
                            int score = Integer.parseInt(input);
                            if (score < 0 || score > 100) {
                                et_studentScore.setError("Score must be between 0 and 100");
                                btn_save.setEnabled(false);
                            } else {
                                if (score < 50) {
                                    et_studentGrad.setText("F");
                                } else if (score < 60) {
                                    et_studentGrad.setText("D");
                                } else if (score < 65) {
                                    et_studentGrad.setText("D+");
                                } else if (score < 70) {
                                    et_studentGrad.setText("C");
                                } else if (score < 75) {
                                    et_studentGrad.setText("C+");
                                } else if (score < 80) {
                                    et_studentGrad.setText("B");
                                } else if (score < 85) {
                                    et_studentGrad.setText("B+");
                                } else if (score < 90) {
                                    et_studentGrad.setText("A");
                                } else if (score <= 100) {
                                    et_studentGrad.setText("A+");
                                }
                            }
                        } catch (NumberFormatException e) {
                            // Handle the exception if the inputis not a valid number
                        }
                    }
                }
            };

            // Add the TextWatcher to the score text field
            et_studentScore.addTextChangedListener(textWatcher);


            // Set a click listener on the save button
            btn_save.setOnClickListener(v -> {
                // Get the student ID
                int studentId = students.getId();

                // Get the degree value
                int degree;
                if (!et_studentScore.getText().toString().trim().isEmpty()) {
                    degree = Integer.parseInt(et_studentScore.getText().toString().trim());
                } else {
                    degree = -1;
                }

                // Get the grade value
                String grade = et_studentGrad.getText().toString();

                // Create a new Enrollments object with the updated grade and degree
                Enrollments enrollments = new Enrollments(studentId, courseID, degree, grade);

                // Add or update the grade in the database
                boolean result = db.addOrUpdateGrade(enrollments);

                if(result){
                    studentsList.set(getAdapterPosition(), students);
                    notifyItemChanged(getAdapterPosition());

                    bottomSheetDialog.dismiss();
                }

                // Dismiss the dialog
                bottomSheetDialog.dismiss();
            });

            // Set a click listener on the close button
            btn_close.setOnClickListener(v -> bottomSheetDialog.dismiss());

            // Set the content view of the dialog and show it
            bottomSheetDialog.setContentView(bottomSheetDialogView);
            bottomSheetDialog.show();
        }

    }
}
