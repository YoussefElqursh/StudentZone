package com.studentzone.Doctor_Classes.Doctor_Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

import java.util.List;

public class DoctorSubjectAdapter extends RecyclerView.Adapter<DoctorSubjectViewHolder> {

    Context context;

    List<DoctorSubjectModel>doctorSubjectModel;

    public DoctorSubjectAdapter(Context context, List<DoctorSubjectModel> doctorSubjectModel) {
        this.context = context;
        this.doctorSubjectModel = doctorSubjectModel;
    }

    @NonNull
    @Override
    public DoctorSubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DoctorSubjectViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_doctor_model_subject,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorSubjectViewHolder holder, int position) {

    holder.StudentName.setText(doctorSubjectModel.get(position).getSubjectName());
    holder.StudentCode.setText(doctorSubjectModel.get(position).getStudentCode());
    holder.StudentIcon.setImageResource(doctorSubjectModel.get(position).getSubjectIcon());

    }

    @Override
    public int getItemCount() {
        return doctorSubjectModel.size();
    }
}
