package com.studentzone.Admin_Calsses.Admin_Models.Admin_Doctors_Accounts_Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

import java.util.List;

public class AdminDoctorAccountAdapter extends RecyclerView.Adapter<AdminDoctorAccountViewHolder> {

    Context context;
    List<AdminDoctorAccountModel>adminDoctorAccountModel;

    public AdminDoctorAccountAdapter(Context context, List<AdminDoctorAccountModel> adminDoctorAccountModel) {
        this.context = context;
        this.adminDoctorAccountModel = adminDoctorAccountModel;
    }

    @NonNull
    @Override
    public AdminDoctorAccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new AdminDoctorAccountViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_admin_model_doctor, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminDoctorAccountViewHolder holder, int position) {

        holder.DoctorName.setText(adminDoctorAccountModel.get(position).getDoctorName());
        holder.DoctorPassword.setText(adminDoctorAccountModel.get(position).getDoctorPassword());
        holder.DoctorIcon.setImageResource(adminDoctorAccountModel.get(position).getDoctorIcon());

    }

    @Override
    public int getItemCount() {
        return adminDoctorAccountModel.size();
    }
}
