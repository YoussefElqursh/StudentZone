package com.studentzone.Admin_Classes.Admin_Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.studentzone.Data_Base.Departments;
import com.studentzone.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class departmentRecyclerViewAdapter extends RecyclerView.Adapter<departmentRecyclerViewAdapter.departmentViewHolder> implements Filterable
{

    ArrayList<Departments> departmentsArrayList;
    ArrayList<Departments> FullList;


    public departmentRecyclerViewAdapter(ArrayList<Departments> departmentsArrayList)
    {
        this.departmentsArrayList = departmentsArrayList;
        FullList =new ArrayList<>(departmentsArrayList);
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

    @Override
    public Filter getFilter() {
        return search_Filter;
    }
    Filter search_Filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Departments> filteredList =new ArrayList<>();
            if (charSequence==null ||charSequence.length() ==0){
                filteredList.addAll(FullList);
            }else {
                String filterPattern =charSequence.toString().toLowerCase().trim();
                for(Departments item :FullList){
                    if (item.getName().toUpperCase().contains(filterPattern))
                    {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults Results =new FilterResults();
            Results.values=filteredList;
            return Results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            departmentsArrayList.clear();
            departmentsArrayList.addAll((ArrayList) filterResults.values);
            notifyDataSetChanged();

        }
    };

    class departmentViewHolder extends RecyclerView.ViewHolder{
        TextView tv_department_name, tv_department_code;
        public departmentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_department_name = itemView.findViewById(R.id.activity_admin_dpartment_tv_sub_name);
            tv_department_code = itemView.findViewById(R.id.activity_admin_dpartment_tv_sub_code);
        }
    }

}
