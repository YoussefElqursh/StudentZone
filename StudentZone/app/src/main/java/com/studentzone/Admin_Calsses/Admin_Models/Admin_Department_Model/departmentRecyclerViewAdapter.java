package com.studentzone.Admin_Calsses.Admin_Models.Admin_Department_Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.studentzone.Data_Base.Departments;
import com.studentzone.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class departmentRecyclerViewAdapter extends RecyclerView.Adapter<departmentRecyclerViewAdapter.departmentViewHolder>
{

    ArrayList<Departments> departmentsArrayList;

    public departmentRecyclerViewAdapter(ArrayList<Departments> departmentsArrayList)
    {
        this.departmentsArrayList = departmentsArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public departmentViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_model_department, null, false);

        departmentViewHolder departmentViewHolder = new departmentViewHolder(view);

        return departmentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull departmentRecyclerViewAdapter.departmentViewHolder holder, int position) {

        Departments departments = departmentsArrayList.get(position);

        holder.tv_department_name.setText(departments.getName());
        holder.tv_department_code.setText(departments.getCode());

    }

    @Override
    public int getItemCount() {
        return departmentsArrayList.size();
    }

    public void addItem(Departments department)
    {
        departmentsArrayList.add(department);
    }

    class departmentViewHolder extends RecyclerView.ViewHolder{
        TextView tv_department_name, tv_department_code;
        public departmentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_department_name = itemView.findViewById(R.id.activity_admin_dpartment_tv_sub_name);
            tv_department_code = itemView.findViewById(R.id.activity_admin_dpartment_tv_sub_code);
        }
    }

}
