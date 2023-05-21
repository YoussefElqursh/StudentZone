package com.studentzone.Doctor_Classes.Doctor_Model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

public class DoctorSubjectViewHolder extends RecyclerView.ViewHolder {

    TextView StudentName,StudentCode;

    ImageView StudentIcon;

    public DoctorSubjectViewHolder(@NonNull View itemView) {
        super(itemView);

        StudentName = itemView.findViewById(R.id.activity_doctor_subject_tv_sub_name);

        StudentCode = itemView.findViewById(R.id.activity_doctor_subject_tv_sub_code);

        StudentIcon = itemView.findViewById(R.id.activity_doctor_subject_iv);

    }
}
