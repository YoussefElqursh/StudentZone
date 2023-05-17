package com.studentzone.Student_Classes.Student_Models;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

public class StudentViewHolder extends RecyclerView.ViewHolder {

    ImageView subject_icon ;

    TextView subject_name ,subject_description;

    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);

        subject_icon = itemView.findViewById(R.id.activity_admin_depatrments_iv);
        subject_name = itemView.findViewById(R.id.activity_admin_depatrments_tv_sub_name);
        subject_description = itemView.findViewById(R.id.activity_admin_depatrments_tv_sub_code);
    }
}
