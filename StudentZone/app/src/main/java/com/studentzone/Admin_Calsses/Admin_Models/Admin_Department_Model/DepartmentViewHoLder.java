package com.studentzone.Admin_Calsses.Admin_Models.Admin_Department_Model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

public class DepartmentViewHoLder extends RecyclerView.ViewHolder {

    TextView DepartmentName ,DepartmentCode;

    ImageView DepartmentIcon;

    public DepartmentViewHoLder(@NonNull View itemView) {
        super(itemView);

        DepartmentName = itemView.findViewById(R.id.activity_admin_dpartment_tv_sub_name);
        DepartmentCode = itemView.findViewById(R.id.activity_admin_dpartment_tv_sub_code);
        DepartmentIcon = itemView.findViewById(R.id.activity_admin_dpartment_iv);

    }
}
