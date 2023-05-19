package com.studentzone.Student_Classes.Student_Models.RegestrationModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

import java.util.List;

public class SubjectRegestrationAdapter extends RecyclerView.Adapter<SubjectRegestrationViewHolder> {

    Context context ;
    List<SubjectRegestrationModel>subjectRegestrationModel;

    public SubjectRegestrationAdapter(Context context, List<SubjectRegestrationModel> subjectRegestrationModel) {
        this.context = context;
        this.subjectRegestrationModel = subjectRegestrationModel;
    }

    @NonNull
    @Override
    public SubjectRegestrationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubjectRegestrationViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_student_model_regestration,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectRegestrationViewHolder holder, int position) {

        holder.subject_name.setText(subjectRegestrationModel.get(position).getSubject_name());
    }

    @Override
    public int getItemCount() {
        return subjectRegestrationModel.size();
    }
}
