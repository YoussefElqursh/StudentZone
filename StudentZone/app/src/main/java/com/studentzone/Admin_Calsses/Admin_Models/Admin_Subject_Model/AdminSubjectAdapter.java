package com.studentzone.Admin_Calsses.Admin_Models.Admin_Subject_Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

import java.util.List;

public class AdminSubjectAdapter extends RecyclerView.Adapter<AdminSubjectViewHolder> {

    Context context;

    List<AdminSubjectModel>adminSubjectModel;

    public AdminSubjectAdapter(Context context, List<AdminSubjectModel> adminSubjectModel) {
        this.context = context;
        this.adminSubjectModel = adminSubjectModel;
    }

    @NonNull
    @Override
    public AdminSubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdminSubjectViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_admin_model_subject, parent ,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminSubjectViewHolder holder, int position) {

        holder.SubjectName.setText(adminSubjectModel.get(position).getSubjectName());

        holder.SubjectCode.setText(adminSubjectModel.get(position).getSubjectCode());

        holder.SubjectIcon.setImageResource(adminSubjectModel.get(position).getSubjectIcon());

    }

    @Override
    public int getItemCount() {
        return adminSubjectModel.size();
    }
}
