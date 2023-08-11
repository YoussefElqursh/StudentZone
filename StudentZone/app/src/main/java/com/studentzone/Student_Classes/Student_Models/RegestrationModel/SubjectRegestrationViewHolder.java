package com.studentzone.Student_Classes.Student_Models.RegestrationModel;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

public class SubjectRegestrationViewHolder extends RecyclerView.ViewHolder {

    TextView subject_name,tv_first_letter_of_course,subject_code;
    CheckBox checkBox ;

    public SubjectRegestrationViewHolder(@NonNull View itemView) {

        super(itemView);

        checkBox =itemView.findViewById(R.id.activity_student_subject_tv_sub_state);
        subject_name =itemView.findViewById(R.id.activity_student_registration_tv_sub_name);
        subject_code =itemView.findViewById(R.id.activity_student_registration_tv_sub_code);
        tv_first_letter_of_course = itemView.findViewById(R.id.activity_student_registration_tv_sn);

    }
}
