package com.studentzone.Admin_Calsses.Admin_Models.Admin_Department_Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

import java.util.List;

public class AdminDepartmentAdaper extends RecyclerView.Adapter<AdminDepartmentViewHoLder> {

    Context context ;
    List<AdminDepartmentModel>departmentModel;

    public AdminDepartmentAdaper(Context context, List<AdminDepartmentModel> departmentModel) {
        this.context = context;
        this.departmentModel = departmentModel;
    }


    @NonNull
    @Override
    public AdminDepartmentViewHoLder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdminDepartmentViewHoLder(LayoutInflater.from(context).inflate(R.layout.activity_admin_model_department, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminDepartmentViewHoLder holder, int position) {

        holder.DepartmentName.setText(departmentModel.get(position).getDepartmentName());
        holder.DepartmentCode.setText(departmentModel.get(position).getDepartmentCode());
        holder.DepartmentIcon.setImageResource(departmentModel.get(position).getDepartmentIcon());

    }

    @Override
    public int getItemCount() {
        return departmentModel.size();
    }
}
