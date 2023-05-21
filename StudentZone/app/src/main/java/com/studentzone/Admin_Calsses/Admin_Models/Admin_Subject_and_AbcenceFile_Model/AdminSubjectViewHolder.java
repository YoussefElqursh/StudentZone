package com.studentzone.Admin_Calsses.Admin_Models.Admin_Subject_and_AbcenceFile_Model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

public class AdminSubjectViewHolder extends RecyclerView.ViewHolder {

    TextView SubjectName , SubjectCode ;

    ImageView SubjectIcon;

    public AdminSubjectViewHolder(@NonNull View itemView) {
        super(itemView);

        SubjectName = itemView.findViewById(R.id.activity_admin_subject_tv_sub_name);

        SubjectCode = itemView.findViewById(R.id.activity_admin_subject_tv_sub_code);

        SubjectIcon = itemView.findViewById(R.id.activity_admin_subject_iv);

    }
}
