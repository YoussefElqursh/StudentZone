package com.studentzone.Admin_Calsses.Admin_Models.Admin_Students_Accounts_Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

import java.util.List;

public class AdminStudentAccountAdapter extends RecyclerView.Adapter<AdminStudentAccountViewHolder> {

    Context context;

    List<AdminStudentAccountModel>adminStudentAccountModel;

    public AdminStudentAccountAdapter(Context context, List<AdminStudentAccountModel> adminStudentAccountModel) {
        this.context = context;
        this.adminStudentAccountModel = adminStudentAccountModel;
    }

    @NonNull
    @Override
    public AdminStudentAccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdminStudentAccountViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_admin_model_student, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminStudentAccountViewHolder holder, int position) {

        holder.StudentName.setText(adminStudentAccountModel.get(position).getStudentName());
        holder.StudentCode.setText(adminStudentAccountModel.get(position).getStudentCode());
        holder.StudentIcon.setImageResource(adminStudentAccountModel.get(position).getStudentIcon());

    }

    @Override
    public int getItemCount() {
        return adminStudentAccountModel.size();
    }
}
