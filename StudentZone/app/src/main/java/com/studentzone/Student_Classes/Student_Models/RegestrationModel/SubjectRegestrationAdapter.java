package com.studentzone.Student_Classes.Student_Models.RegestrationModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

import java.util.List;
import java.util.Locale;

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

        SubjectRegestrationModel course = subjectRegestrationModel.get(position);

        String  abbreviation = "",courseName = course.subject_name;
        String[] words = courseName.split(" ");
        for (String word : words) {
            char firstLetter = word.charAt(0);
            abbreviation += firstLetter;
        }

        holder.subject_name.setText(subjectRegestrationModel.get(position).subject_name);
        holder.subject_code.setText(subjectRegestrationModel.get(position).subject_code);
        holder.tv_first_letter_of_course.setText(abbreviation.toUpperCase(Locale.ROOT));
    }

    @Override
    public int getItemCount() {
        return subjectRegestrationModel.size();
    }
}
