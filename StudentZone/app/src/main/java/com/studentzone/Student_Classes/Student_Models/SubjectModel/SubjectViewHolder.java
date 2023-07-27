package com.studentzone.Student_Classes.Student_Models.SubjectModel;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

public class SubjectViewHolder extends RecyclerView.ViewHolder {



    TextView subject_name ,subject_description,tv_first_letter_of_course;

    public SubjectViewHolder(@NonNull View itemView) {
        super(itemView);

        subject_name = itemView.findViewById(R.id.activity_student_subject_tv_sub_name);
        subject_description = itemView.findViewById(R.id.activity_student_subject_tv_sub_code);
        tv_first_letter_of_course  = itemView.findViewById(R.id.activity_student_subject_tv_sn);
    }
}
