package com.studentzone.Admin_Calsses.Admin_Models.Admin_Department_Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

import java.util.List;

public class DepartmentAdaper extends RecyclerView.Adapter<DepartmentViewHoLder> {

    Context context ;
    List<DepartmentModel>departmentModel;

    public DepartmentAdaper(Context context, List<DepartmentModel> departmentModel) {
        this.context = context;
        this.departmentModel = departmentModel;
    }


    @NonNull
    @Override
    public DepartmentViewHoLder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DepartmentViewHoLder(LayoutInflater.from(context).inflate(R.layout.activity_admin_model_department, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentViewHoLder holder, int position) {

        holder.DepartmentName.setText(departmentModel.get(position).getDepartmentName());
        holder.DepartmentCode.setText(departmentModel.get(position).getDepartmentCode());
        holder.DepartmentIcon.setImageResource(departmentModel.get(position).getDepartmentIcon());

    }

    @Override
    public int getItemCount() {
        return departmentModel.size();
    }
}
