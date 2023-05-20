package com.studentzone.Admin_Calsses.Admin_Models.Admin_Students_Accounts_Model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

public class AdminStudentAccountViewHolder extends RecyclerView.ViewHolder {

    TextView StudentName,StudentCode;

    ImageView StudentIcon;


    public AdminStudentAccountViewHolder(@NonNull View itemView) {
        super(itemView);

        StudentName = itemView.findViewById(R.id.activity_admin_student_tv_sub_name);

        StudentCode = itemView.findViewById(R.id.activity_admin_student_tv_sub_code);

        StudentIcon = itemView.findViewById(R.id.activity_admin_student_iv);

    }
}
