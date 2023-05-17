package com.studentzone.Student_Classes.Student_Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder>{

    Context context ;

    public StudentAdapter(Context context, List<StudentModel> studentSubjectModel) {
        this.context = context;
        this.studentSubjectModel = studentSubjectModel;
    }

    List<StudentModel>studentSubjectModel;

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_student_model_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.subject_icon.setImageResource(studentSubjectModel.get(position).getSubject_icon());
        holder.subject_name.setText(studentSubjectModel.get(position).getSubject_name());
        holder.subject_description.setText(studentSubjectModel.get(position).getSubject_description());

    }

    @Override
    public int getItemCount() {
        return studentSubjectModel.size();
    }
}
