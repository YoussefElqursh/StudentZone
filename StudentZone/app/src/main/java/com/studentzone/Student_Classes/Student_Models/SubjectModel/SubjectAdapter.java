package com.studentzone.Student_Classes.Student_Models.SubjectModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectViewHolder>{

    Context context ;
    List<SubjectModel>studentSubjectModel;

    public SubjectAdapter(Context context, List<SubjectModel> SubjectAdapter) {
        this.context = context;
        this.studentSubjectModel = SubjectAdapter;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubjectViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_student_model_subject,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        holder.subject_icon.setImageResource(studentSubjectModel.get(position).getSubject_icon());
        holder.subject_name.setText(studentSubjectModel.get(position).getSubject_name());
        holder.subject_description.setText(studentSubjectModel.get(position).getSubject_description());

    }

    @Override
    public int getItemCount() {
        return studentSubjectModel.size();
    }
}
