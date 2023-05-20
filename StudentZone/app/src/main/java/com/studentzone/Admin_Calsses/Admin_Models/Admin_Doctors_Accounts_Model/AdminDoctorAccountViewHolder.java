package com.studentzone.Admin_Calsses.Admin_Models.Admin_Doctors_Accounts_Model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

public class AdminDoctorAccountViewHolder extends RecyclerView.ViewHolder {

    TextView DoctorName ,DoctorPassword ;

    ImageView DoctorIcon ;

    public AdminDoctorAccountViewHolder(@NonNull View itemView) {
        super(itemView);

        DoctorName = itemView.findViewById(R.id.activity_admin_doctor_tv_sub_name);

        DoctorPassword = itemView.findViewById(R.id.activity_admin_doctor_tv_sub_code);

        DoctorIcon = itemView.findViewById(R.id.activity_admin_doctor_iv);

    }
}
