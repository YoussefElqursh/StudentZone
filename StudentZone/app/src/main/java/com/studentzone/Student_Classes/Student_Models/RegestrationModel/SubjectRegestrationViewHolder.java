package com.studentzone.Student_Classes.Student_Models.RegestrationModel;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

public class SubjectRegestrationViewHolder extends RecyclerView.ViewHolder {

    TextView subject_name;
    CheckBox checkBox ;

    public SubjectRegestrationViewHolder(@NonNull View itemView) {

        super(itemView);

        subject_name =itemView.findViewById(R.id.activity_student_Regestration_tv_sub_name);
        checkBox =itemView.findViewById(R.id.activity_student_subject_tv_sub_state);

    }
}
